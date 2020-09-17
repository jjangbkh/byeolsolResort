package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.dto.Answer;
import model.dto.Question;
import model.service.AnswerService;
import model.service.CustomerService;
import model.service.QuestionService;
import model.view.AnswerView;
import model.view.QuestionView;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	CustomerService customerservice;

	@Autowired
	AnswerService answerService;

	@GetMapping("/addQuestion")
	public String addQuestionForm(HttpSession session, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String)session.getAttribute("userId");
			if(!userId.equals("admin")) {
			return "/mypage/qna";
			}else {
				return "redirect:/cus/myPage";
			}
		} else {
			m.addAttribute("errorMessage","로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/addQuestion")
	public String addQuestion(HttpSession session, Question question, Model m) {
		if(session.getAttribute("userId")!=null) { // 로그인이 되어있으며 question의 작성자를 로그인 되어있는 Id로
		question.setWriter((String) session.getAttribute("userId"));
		if(questionService.nullCheck(question)) { // 빈값이 들어 갔는지 확인
		questionService.addQuestion(question);	// 추가
		return "redirect:/question/list";
		}else {
			m.addAttribute("errorMessage","입력 하지 않은 값이 있습니다.");
			return "redirect:/question/addQuestion";
		}
		}else {
		m.addAttribute("errorMessage","로그인이 되어 있지 않습니다.");
		return "redirect:/index/main";
		}
	}

	@GetMapping("/list")
	public String getQuestionView(HttpSession session, @RequestParam(defaultValue = "1") int pageNum, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (!userId.equals("admin")) {	// 관리자가 아닌 사용자가 로그인 되어 있을 때  자신이 쓴 question을 페이지에 view를 이용해 보여줌
				if (customerservice.getCustomerById(userId) != null) {
					QuestionView questionView = questionService.getQuestionView(pageNum, userId);
					m.addAttribute("questionView", questionView);
					return "/mypage/myQnA";
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage","잘못된 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/detailQuestion")
	public String goDetailQuestion(HttpSession session, @RequestParam(defaultValue = "0") int id, Model m,
			@RequestParam(defaultValue = "1") int pageNum) {	// id가 들어오지 않으면 0으로 하고 pageNum도 들어오지 않으면 1로 넣음
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) { // id가 0이고
				Question question = questionService.selectQuestionById(id);
				if (question != null && question.getWriter().equals(userId) || userId.equals("admin")) { 
					// question이 null이 아니고 question을 쓴 사람과 로그인을 한 사람이 같거나 관리자 이면
					// 답변 들과 question을 보여준다.
					AnswerView answerView = answerService.getAnswerView(pageNum, id);
					m.addAttribute("answerView", answerView);
					m.addAttribute("question", question);
					return "/mypage/detailMyQnA";
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/question/list";
			}

		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}
	
	@GetMapping("/detailAdminQuestion")
	public String goDetailAdminQuestion(HttpSession session, @RequestParam(defaultValue = "0") int id, Model m,
			@RequestParam(defaultValue = "1") int pageNum) {	// id가 들어오지 않으면 0으로 하고 pageNum도 들어오지 않으면 1로 넣음
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) { // id가 0이고
				Question question = questionService.selectQuestionById(id);
				if (question != null && question.getWriter().equals(userId) || userId.equals("admin")) { 
					// question이 null이 아니고 question을 쓴 사람과 로그인을 한 사람이 같거나 관리자 이면
					// 답변 들과 question을 보여준다.
					AnswerView answerView = answerService.getAnswerView(pageNum, id);
					m.addAttribute("answerView", answerView);
					m.addAttribute("question", question);
					return "/adminPage/detailAdminQnA";
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/index/adminQnA";
			}

		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping("/addAnswer")
	public String addAnswerP(Answer answer, HttpSession session, @RequestParam(defaultValue = "1") int pageNum, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			answer.setWriter(userId);
			//추가 하려는 답변에 공백 값이 있는지 확인 하고 없다면 추가
			if(answerService.nullCheck(answer)) {
			answerService.addAnswer(answer);
			return "redirect:/question/detailQuestion?id=" + answer.getQuestionId() + "&pageNum=" + pageNum;
			}else {
				m.addAttribute("errorMessage","입력 하지 않은 값이 있습니다.");
				return "redirect:/question/detailQuestion?id=" + answer.getQuestionId() + "&pageNum=" + pageNum;
			}
			
		} else {
			return "redirect:/index/main";
		}
	}
	
	@PostMapping("/addAdminAnswer")
	public String addAdminAnswerP(Answer answer, HttpSession session, @RequestParam(defaultValue = "1") int pageNum, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			answer.setWriter(userId);
			//추가 하려는 답변에 공백 값이 있는지 확인 하고 없다면 추가
			if(answerService.nullCheck(answer)) {
			answerService.addAnswer(answer);
			return "redirect:/question/detailAdminQuestion?id=" + answer.getQuestionId() + "&pageNum=" + pageNum;
			}else {
				m.addAttribute("errorMessage","입력 하지 않은 값이 있습니다.");
				return "redirect:/question/detailAdminQuestion?id=" + answer.getQuestionId() + "&pageNum=" + pageNum;
			}
			
		} else {
			return "redirect:/index/main";
		}
	}

	@GetMapping("/updateQuestion")
	public String updateQuestionForm(HttpSession session, @RequestParam(defaultValue = "0") int id, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) {
				Question question = questionService.selectQuestionById(id);
				if (question != null && question.getWriter().equals(userId)) { // 받아온 question이 null이 아니고 작성자가 로그인 되어있는 사람이라면
					m.addAttribute("question", question);
					return "/mypage/updateMyQnA";
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/question/list";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/updateQuestion")
	public String updateQuestion(HttpSession session, Question question, Model m) {
		Question qu = questionService.selectQuestionById(question.getId());
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals(qu.getWriter())) {
				if(questionService.nullCheck(question)) {
				questionService.updateQuestion(question);
				return "redirect:/question/list";
				}else {
					m.addAttribute("errorMessage","입력하지 않은 값이 있습니다.");
					return "redirect:/question/updateQuestion?id="+question.getId();
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/updateAnswer")
	public String updateAnswer(HttpSession session, @RequestParam(defaultValue = "0") int id, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) {
				Answer answer = answerService.getAnswerById(id);
				if(answer!=null && answer.getWriter().equals(userId)) {
				m.addAttribute("answer", answer);
				return "/mypage/updateAnswerMyQnA";
				}else {
					m.addAttribute("errorMessage","잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/updateAnswer")
	public String updateAnswerP(HttpSession session, Answer answer, Model m) {
		Answer an = answerService.getAnswerById(answer.getId());
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals(an.getWriter())) { // 로그인 되어 있는 사람의 Id와 작성자가 같다면
				answer.setWriter(an.getWriter());	// answer의 Writer를 바꾸기전 answer의 작성자로
				if(answerService.nullCheck(answer)) {
				answerService.updateAnswer(answer);
				return "redirect:/question/list";
				}else {
					m.addAttribute("errorMessage","입력 하지 않은 값이 있습니다.");
					return "redirect:/question/updateAnswer?id="+answer.getId();
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/index/main";

			}
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}

	}

	@GetMapping("/deleteQuestion")
	public String deleteQuestion(HttpSession session, @RequestParam(defaultValue = "0") int id, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) {
				Question question = questionService.selectQuestionById(id);
				if (question != null) {	// question이 null이 아니고 로그인  되어있는 사람의 Id와 question의 작성자 와 같거나 로그인 되어 있는게 관리자 이면
					if (userId.equals(question.getWriter()) || userId.equals("admin")) {
						// 답변과 question을 삭제
						answerService.deleteAnswerByQuestionId(id);
						questionService.deleteQuestion(id);
						return "redirect:/question/list";
					} else {
						m.addAttribute("errorMessage", "잘못된 접근 입니다.");
						return "redirect:/index/main";
					}
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/question/list";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/question/list";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@GetMapping("/deleteAnswer")
	public String deleteAnswer(HttpSession session, int id, Model m) {

		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (id > 0) {
				Answer answer = answerService.getAnswerById(id);
				// 작성자와 로그인 되어있는 Id가 
				if (answer != null && userId.equals(answer.getWriter())) {
					answerService.deleteAnswer(id);
					return "redirect:/question/list";
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/index/main";
			}

		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}

	}

}
