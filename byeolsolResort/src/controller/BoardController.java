package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.dto.Board;
import model.dto.Comment;
import model.service.BoardService;
import model.service.CommentService;
import model.service.FtpService;
import model.view.BoardView;

@Controller
@RequestMapping("/board") // controller mapping
public class BoardController {
	@Autowired
	BoardService boardService;

	@Autowired
	CommentService commentService;

	@GetMapping("/list")
	public String getList(Model m, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(required = false) String errorMessage) {
		// adBoardList 와 boardView를 page에 줌
		m.addAttribute("adBoardList", boardService.getAdBoard());
		m.addAttribute("boardView", boardService.getView(pageNum));
		if (errorMessage != null)
			m.addAttribute("errorMessage", errorMessage);
		return "/serviceList/board";
	}

	@GetMapping("/addBoard")
	public String goAddBoardForm(HttpSession session, Model m , @RequestParam(defaultValue = "")String errorMessage) {
		if (session.getAttribute("userId") != null) {// session에 userId가 있다면
			if(!errorMessage.equals(""))
				m.addAttribute("errorMessage",errorMessage);
			return "/serviceList/addBoard"; // WEB_INF/erviceList/addBoard jsp페이지
		}
		else {
			// session에 로그인이 되어있지 않다면 errorMessage를 보냄
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/board/list";
		}
	}

	@Autowired
	FtpService ftpService;

	@PostMapping("/addBoard")
	public String goAddBoardResult(Board board, HttpSession session,
			@RequestParam(required = false) MultipartFile[] uploadFile, Model m) {
		boolean isTypeCheck = true;
		boolean errorCheck = true;
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			board.setUserId(userId);
			// board의 title과 content가 null인지 확인 후 request
			if (!boardService.nullCheck(board)) {
				m.addAttribute("errorMessage", "공백이 있습니다.");
				return "redirect:/board/addBoard";

			} else { // 아니라면
				if (uploadFile.length <= 3) {
					// Board의 추가하는 시간을 갖옴
					String addTime = LocalDateTime.now()
							.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSSS"));
					// upload파일중 0 번 파일이 비어있지 않다면
					if (!uploadFile[0].isEmpty()) {
						// uploadFile의 수 만큼 반복
						for (int i = 0; i < uploadFile.length; i++) {
							System.out.println(i);
							// 파일 이미지 타입을 체크
							if (boardService.checkImg(uploadFile[i])) {
								if (i == 0) {
									// i 가 0 이면 ftp에 board 와 시간 안쪽 폴더에 이미지를 올리고 imgPath를 셋팅
									board = boardService.imgUpAndSetPath(board, i, uploadFile[i], addTime, "first");
									if (board.getFirstPath() == null)
										errorCheck = false;
								}
								if (i == 1) {
									board = boardService.imgUpAndSetPath(board, i, uploadFile[i], addTime, "second");
									if (board.getSecondPath() == null)
										errorCheck = false;
								}
								if (i == 2) {
									board = boardService.imgUpAndSetPath(board, i, uploadFile[i], addTime, "third");
									if (board.getThirdPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}

						}
					}
					if (isTypeCheck && errorCheck) { // type체크와 errorChek가 된다면
						boardService.addBoard(board);
						return "redirect:/board/list";
					} else {
						m.addAttribute("errorMessage", "게시글 형식에 맞지 않습니다.");
						return "redirect:/board/addBoard";
					}
				} else {
					m.addAttribute("errorMessage", "파일은 3개 까지만 넣을 수 있습니다.");
					return "redirect:/board/addBoard";
				}
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/board/list";
		}
	}

	@GetMapping("/adminList")
	public String getadminList(Model m, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(required = false) String errorMessage) {
		// boardView를 가져와서 페이지에 줌
		m.addAttribute("boardView", boardService.getAdminBoardView(pageNum));
		if (errorMessage != null)
			m.addAttribute("errorMessage", errorMessage);
		return "/newsList/news";
	}

	@GetMapping("/addAdminBoard")
	public String addAdminBoardForm(HttpSession session, Model m , @RequestParam(defaultValue = "")String errorMessage) {

		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) { // session의 userId가 있고 그게 admin일 경우
				if(!errorMessage.equals(""))
					m.addAttribute("errorMessage",errorMessage);
				return "/newsList/addNews";
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/board/adminList";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping("/addAdminBoard")
	public String addAdminBoard(Board board, HttpSession session,
			@RequestParam(required = false) MultipartFile[] uploadFile, Model m) {
		boolean isTypeCheck = true; // 위 addBoard와 같음
		boolean errorCheck = true;
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				board.setUserId(userId);
				if (!boardService.nullCheck(board)) {
					m.addAttribute("errorMessage", "공백이 있습니다.");
					return "redirect:/board/addAdminBoard";

				} else {
					String addTime = LocalDateTime.now()
							.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSSS"));
					if (!uploadFile[0].isEmpty()) {
						for (int i = 0; i < uploadFile.length; i++) {
							System.out.println(i);
							if (boardService.checkImg(uploadFile[i])) {
								if (i == 0) {
									board = boardService.imgUpAndSetPath(board, i, uploadFile[i], addTime, "first");
									if (board.getFirstPath() == null)
										errorCheck = false;
								}
								if (i == 1) {
									board = boardService.imgUpAndSetPath(board, i, uploadFile[i], addTime, "second");
									if (board.getSecondPath() == null)
										errorCheck = false;
								}
								if (i == 2) {
									board = boardService.imgUpAndSetPath(board, i, uploadFile[i], addTime, "third");
									if (board.getThirdPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}

						}
					}
					if (isTypeCheck && errorCheck) {
						board.setState("admin");
						boardService.addBoard(board);
						return "redirect:/board/adminList";
					} else {
						m.addAttribute("errorMessage", "게시글 형식에 맞지 않습니다.");
						return "redirect:/board/addAdminBoard";
					}
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/board/adminList";
		}
	}

	@GetMapping("/updateBoard")
	public String goBoardUpdateForm(@RequestParam(defaultValue = "0") int id, Model m, HttpSession session, @RequestParam(defaultValue = "")String errorMessage) {
		System.out.println(id);
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id != 0) {
				Board board = boardService.selectBoard(id);
				if (userId.equals(board.getUserId()) || userId.equals("admin")) {
					if(!errorMessage.equals(""))
						m.addAttribute("errorMessage",errorMessage);
					m.addAttribute("board", board);
					return "/serviceList/updateBoard";
				} else {
					m.addAttribute("errorMessage", "권한이 없습니다.");
					return "redirect:/board/list";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 요청 방식 입니다.");
				return "redirect:/board/list";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/board/list";
		}

	}

	@PostMapping("/updateBoard")
	public String goUpdateBoardResult(Board board, HttpSession session, Model m,
			@RequestParam(required = false) MultipartFile uploadFile01,
			@RequestParam(required = false) MultipartFile uploadFile02,
			@RequestParam(required = false) MultipartFile uploadFile03) {
		boolean isTypeCheck = true;
		boolean errorCheck = true;
		board.getId();
		if (session.getAttribute("userId") != null) {
			String updateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSSS"));
			// 페이지에서 board와 Id 를 받아와 board를 찾아모
			Board b = boardService.selectBoard(board.getId());
			if (b != null) { // 가져온 보드가 null이 아니라면
				String userId = (String) session.getAttribute("userId");
				// 게시글의 작성자와 로그인 되어있는 userId 와 같거나 userId와 admin일 경우
				if (b.getUserId().equals(userId) || userId.equals("admin")) {
					// board의 userId를 session에 있는 userId로 셋팅
					board.setUserId(b.getUserId());
					if (boardService.nullCheck(board)) {
						if (uploadFile01.isEmpty()) { // 파일이 비어있다면 board의 Path를 아까 가져온 board b의 Path로 지정
							board.setFirstPath(b.getFirstPath());
						} else { // 비어있지 않다면 파일의 이미지를 체크하고
							if (boardService.checkImg(uploadFile01)) {
								if (b.getFirstPath() != null) { // 아까 가져온 board b의 path의 시간을 가져와서 path설정
									String Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
											b.getFirstPath().lastIndexOf('/'));
									// ftp 이미지 삭제
									ftpService.ftpdelete(b.getFirstPath(), Time);
									board = boardService.imgUpAndSetPath(board, 0, uploadFile01, Time, "first");
									if (board.getFirstPath() == null)
										errorCheck = false;
								} else {
									board = boardService.imgUpAndSetPath(board, 0, uploadFile01, updateTime, "first");
									if (board.getFirstPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}
						}
						if (uploadFile02.isEmpty()) {
							board.setSecondPath(b.getSecondPath());
						}

						else {
							if (boardService.checkImg(uploadFile02)) {
								if (b.getSecondPath() != null) {
									String Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
											b.getFirstPath().lastIndexOf('/'));
									ftpService.ftpdelete(b.getSecondPath(), Time);
									board = boardService.imgUpAndSetPath(board, 1, uploadFile02, Time, "second");
									if (board.getSecondPath() == null)
										errorCheck = false;
								} else {
									board = boardService.imgUpAndSetPath(board, 1, uploadFile02, updateTime, "second");
									if (board.getSecondPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}
						}

						if (uploadFile03.isEmpty()) {
							board.setThirdPath(b.getThirdPath());
						} else {
							if (boardService.checkImg(uploadFile03)) {
								if (b.getThirdPath() != null) {
									String Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
											b.getFirstPath().lastIndexOf('/'));
									ftpService.ftpdelete(b.getThirdPath(), Time);
									board = boardService.imgUpAndSetPath(board, 2, uploadFile03, Time, "third");
									if (board.getThirdPath() == null)
										errorCheck = false;
								} else {
									board = boardService.imgUpAndSetPath(board, 2, uploadFile03, updateTime, "third");
									if (board.getThirdPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}
						}
						System.out.println("type : " + isTypeCheck);
						System.out.println("error : " + errorCheck);
						if (isTypeCheck && errorCheck) { // typeCheck와 errorCCheck 맞으면
							System.out.println(board.getContent());
							// 업데이트
							boardService.updateBoard(board);
						}
						return "redirect:/board/list";
					} else {
						m.addAttribute("errorMessage", "들어가지 않은 값이 있습니다.");
						return "redirect:/board/updateBoard?id=" + board.getId();
					}
				} else {
					m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
					return "redirect:/board/list";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/list";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/board/list";
		}
	}

	@GetMapping("/updateAdminBoard")
	public String updateAdminBoardForm(HttpSession session, Model m, @RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "")String errorMessage) {
		if (session.getAttribute("userId") != null) {
			if (id > 0) {
				String userId = (String) session.getAttribute("userId");
				if (userId.equals("admin")) {
					Board board = boardService.selectBoard(id);
					if (board != null && board.getState().equals("admin")) {
						m.addAttribute("board", board);
						if(!errorMessage.equals(""))
							m.addAttribute("errorMessage",errorMessage);
						return "/newsList/updateNews";
					} else {
						m.addAttribute("errorMessage", "잘못된 접근 입니다.");
						return "redirect:/board/adminList";
					}
				} else {
					m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/adminList";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping("/updateAdminBoard")
	public String updateAdminBoard(Board board, HttpSession session, Model m,
			@RequestParam(required = false) MultipartFile uploadFile01,
			@RequestParam(required = false) MultipartFile uploadFile02,
			@RequestParam(required = false) MultipartFile uploadFile03) {
		// update Board와 같음
		boolean isTypeCheck = true;
		boolean errorCheck = true;
		if (session.getAttribute("userId") != null) {
			String updateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSSS"));
			Board b = boardService.selectBoard(board.getId());
			if (b != null) {
				String userId = (String) session.getAttribute("userId");
				if (userId.equals("admin") && b.getState().equals("admin")) {
					board.setUserId(b.getUserId());
					if (boardService.nullCheck(board)) {
						if (uploadFile01.isEmpty()) {
							board.setFirstPath(b.getFirstPath());
						} else {
							if (boardService.checkImg(uploadFile01)) {
								if (b.getFirstPath() != null) {
									String Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
											b.getFirstPath().lastIndexOf('/'));
									ftpService.ftpdelete(b.getFirstPath(), Time);
									board = boardService.imgUpAndSetPath(board, 0, uploadFile01, Time, "first");
									if (board.getFirstPath() == null)
										errorCheck = false;
								} else {
									board = boardService.imgUpAndSetPath(board, 0, uploadFile01, updateTime, "first");
									if (board.getFirstPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}
						}
						if (uploadFile02.isEmpty()) {
							board.setSecondPath(b.getSecondPath());
						}

						else {
							if (boardService.checkImg(uploadFile02)) {
								if (b.getSecondPath() != null) {
									String Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
											b.getFirstPath().lastIndexOf('/'));
									ftpService.ftpdelete(b.getSecondPath(), Time);
									board = boardService.imgUpAndSetPath(board, 1, uploadFile02, Time, "second");
									if (board.getSecondPath() == null)
										errorCheck = false;
								} else {
									board = boardService.imgUpAndSetPath(board, 1, uploadFile02, updateTime, "second");
									if (board.getSecondPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}
						}

						if (uploadFile03.isEmpty()) {
							board.setThirdPath(b.getThirdPath());
						} else {
							if (boardService.checkImg(uploadFile03)) {
								if (b.getThirdPath() != null) {
									String Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
											b.getFirstPath().lastIndexOf('/'));
									ftpService.ftpdelete(b.getThirdPath(), Time);
									board = boardService.imgUpAndSetPath(board, 2, uploadFile03, Time, "third");
									if (board.getThirdPath() == null)
										errorCheck = false;
								} else {
									board = boardService.imgUpAndSetPath(board, 2, uploadFile03, updateTime, "third");
									if (board.getThirdPath() == null)
										errorCheck = false;
								}
							} else {
								isTypeCheck = false;
							}
						}
						if (isTypeCheck && errorCheck) {
							boardService.updateBoard(board);
						}
						return "redirect:/board/adminList";
					} else {
						m.addAttribute("errorMessage", "들어가지 않은 값이 있습니다.");
						return "redirect:/board/updateAdminBoard?id=" + board.getId();
					}
				} else {
					m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
					return "redirect:/board/adminList";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/adminList";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/board/adminList";
		}
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(HttpSession session, Model m, @RequestParam(defaultValue = "0") int id) {
		// session의 userId가 null이 아니고 admin일 경우 id를 갖고 board 삭제
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) {
				Board board = boardService.selectBoard(id);
				if (board != null) {
					if (board.getUserId().equals(userId) || userId.equals("admin")) {
						boardService.deleteBoard(id, userId);
						return "redirect:/board/list";
					} else {
						m.addAttribute("errorMessage", "권한이 없습니다.");
						return "redirect:/index/main";
					}
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/board/list";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/list";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/deleteAdminBoard")
	public String deleteAdminBoard(HttpSession session, Model m, @RequestParam(defaultValue = "0") int id) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) {
				Board board = boardService.selectBoard(id);
				if (board != null && board.getState().equals("admin")) {
					if (userId.equals("admin")) {
						boardService.deleteBoard(id, userId);
						return "redirect:/board/adminList";
					} else {
						m.addAttribute("errorMessage", "권한이 없습니다.");
						return "redirect:/index/main";
					}
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/board/adminList";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/adminList";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/detailBoard")
	public String goBoardDetail(int boardId, Model m, @RequestParam(defaultValue = "1") int pageNum ,@RequestParam(defaultValue = "")String errorMessage) {
		Board board = boardService.selectBoard(boardId);
		if (board != null) {
			m.addAttribute("board", boardService.selectBoard(boardId));
			m.addAttribute("commentView", commentService.getView(pageNum, boardId));
			if(!errorMessage.equals(""))
				m.addAttribute("errorMessage",errorMessage);
			return "/serviceList/detailBoard";
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/board/list";
		}
	}

	@GetMapping("/adminDetailBoard")
	public String goAdminBoardDetail(int boardId, Model m, @RequestParam(defaultValue = "1") int pageNum , @RequestParam(defaultValue = "")String errorMessage) {
		Board board = boardService.selectBoard(boardId);
		if (board != null) {
			if(!errorMessage.equals(""))
				m.addAttribute("errorMessage",errorMessage);
			m.addAttribute("board", boardService.selectBoard(boardId));
			m.addAttribute("commentView", commentService.getView(pageNum, boardId));
			return "/newsList/detailNews";
		} else {
			m.addAttribute("errorMessage", "잘못된 접근입니다.");
			return "redirect:/board/adminList";
		}
	}

	@PostMapping("/addComment")
	public String addComment(Comment comment, HttpSession session, Model m) {
		if (session.getAttribute("userId") != null) {
			comment.setUserId((String) session.getAttribute("userId"));
			if (commentService.nullCheck(comment)) {
				commentService.addComment(comment);
			} else {
				m.addAttribute("errorMessage", "입력하지 않은 값이 있습니다.");
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
		}
		return "redirect:/board/detailBoard?boardId=" + comment.getBoardId();
	}

	@GetMapping("/updateComment")
	public String updateCommentForm(HttpSession session, Model m, @RequestParam(defaultValue = "0") int boardId,
			@RequestParam(defaultValue = "0") int id , @RequestParam(defaultValue = "")String errorMessage) {

		if (session.getAttribute("userId") != null) {
			if (id > 0 && boardId > 0) {
				Comment comment = commentService.getCommentWithId(id);
				if (comment != null) {
						String userId = (String) session.getAttribute("userId");
						if (userId.equals(comment.getUserId()) || userId.equals("admin")) {
							if (comment.getBoardId() == boardId) {
								m.addAttribute("boardId",boardId);
								m.addAttribute("comment",comment);
								if(!errorMessage.equals(""))
									m.addAttribute("errorMessage",errorMessage);
								return "/serviceList/updateAnswerBoard";
							} else {
								m.addAttribute("errorMessage", "잘못된 접근 입니다.");
								return "redirect:/board/list";
							}
						}else {
							m.addAttribute("errorMessage","권한이 없는 접근 입니다.");
							return "redirect:/board/list";
						}
				}else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/board/list";
				}
			}else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/list";
			}
		}else {
			m.addAttribute("errorMessage","로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}
	
	@PostMapping("/updateComment")
	public String updateCommentResult(HttpSession session, Model m , Comment comment) {
		if(session.getAttribute("userId")!=null) {
			String userId = (String)session.getAttribute("userId");
			if(comment.getId()>0 && commentService.nullCheck(comment)) {
				Comment cm = commentService.getCommentWithId(comment.getId());
				if(cm!=null) {
					if(cm.getUserId()!=null && cm.getUserId().equals(userId) || cm.getUserId()!=null&& userId.equals("admin")) {
						if(comment.getBoardId()>0 && comment.getBoardId() == cm.getBoardId()) {
							System.out.println(comment);
							commentService.updateComment(comment);
							return "redirect:/board/detailBoard?boardId="+comment.getBoardId();
						}else {
							m.addAttribute("errorMessage", "잘못된 접근 입니다.");
							return "redirect:/board/list";
						}
					}else {
						m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
						return "redirect:/board/list";
					}
				}else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/board/list";
				}
			}else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/list";
			}
		}else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}
	
	@GetMapping("/deleteComment")
	public String deleteComment(HttpSession session, Model m, @RequestParam(defaultValue = "0")int id, @RequestParam(defaultValue = "0")int boardId) {
		if(session.getAttribute("userId")!=null) {
			String userId = (String) session.getAttribute("userId");
			if(id>0 && boardId>0) {
				Comment comment = commentService.getCommentWithId(id);
				if(comment != null&& comment.getBoardId()==boardId) {
					System.out.println(comment.getUserId());
					System.out.println(userId);
					if(comment.getUserId().equals(userId) || userId.equals("admin")) {
						commentService.deleteComment(id, userId);
						return "redirect:/board/detailBoard?boardId="+boardId;
					}else {
						m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
						return "redirect:/board/detailBoard?boardId="+boardId;
					}
				}else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/board/list";
				}
			}else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/board/list";
			}
		}else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";	
		}
	}
	
}
