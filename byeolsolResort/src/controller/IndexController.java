package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import model.dto.Customer;
import model.dto.Reserv;
import model.dto.Room;
import model.service.CustomerService;
import model.service.FtpService;
import model.service.IndexService;
import model.service.QuestionService;
import model.service.ReservService;
import model.service.RoomService;

@Controller
@RequestMapping("/index")
public class IndexController {

	private final String DEFAULT_CLASSIFICATION ="DEFAULT_CLASSIFICATION";
	private final String DEFAULT_VALUE ="DEFAULT_VALUE";
	
	@Autowired
	IndexService indexService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	RoomService roomService;
	
	
	// 어드민페이지
	// Q&A현황
	@GetMapping("/adminQnA")
	public String adminQnAPage(HttpSession session, Model m , @RequestParam(defaultValue = "1")int pageNum) {
		if(session.getAttribute("userId") != null) {
			String userId = (String)session.getAttribute("userId");
			if(userId.equals("admin")) {
				m.addAttribute("questionView",questionService.getQuestionViewWithState(pageNum));
				return "/adminPage/adminQnA";
			}else {
				m.addAttribute("errorMessage","권한이 없는 접근입니다.");
				return "redirect:/index/main";
			}
		}else {
			m.addAttribute("errorMessage","로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}
	
	@Autowired
	ReservService reservService;

	// 객실정보관리
	@GetMapping("/adminRoom")
	public String adminRoomPage(HttpSession session, Model m, @RequestParam(defaultValue = "1") int pageNum) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				m.addAttribute("reservInfoView", reservService.getAdminReservInfoView(pageNum));
				return "adminPage/adminRoom";
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	// 유저정보관리
	@GetMapping("/adminUser")
	public String adminUserPage(HttpSession session, Model m) {
		if(session.getAttribute("userId")!=null) {
			String userId = (String) session.getAttribute("userId");
			if(userId.equals("admin")) {
			return "/adminPage/adminUser";
			}else {
				m.addAttribute("errorMessage","권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		}else {
			m.addAttribute("errorMessage","로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
			
	}

	// 메인페이지
	@GetMapping("/main")
	public String mainPage(@RequestParam(required = false) String errorMessage, Model m) {
		if(errorMessage != null)
		m.addAttribute("errorMessage", errorMessage);
		return "/main/main";
	}

	// 별솔리조트 페이지
	// 별솔리조트란?
	@GetMapping("/byeolsolInfo")
	public String explicateLisPage() {
		return "/explicateList/byeolsolInfo";
	}

	// 객실소개
	@GetMapping("/roomMain")
	public String roomMainPage() {
		return "/explicateList/roomMain";
	}

	// 객실1
	@GetMapping("/roomInfo_01")
	public String roomInfo1Page() {
		return "/explicateList/roomInfo_01";
	}

	// 객실2
	@GetMapping("/roomInfo_02")
	public String roomInfo2Page() {
		return "/explicateList/roomInfo_02";
	}

	@GetMapping("/roomInfo_03")
	public String roomInfo3Page() {
		return "/explicateList/roomInfo_03";
	}

	// 이용안내
	@GetMapping("/fee")
	public String feePage() {
		return "/explicateList/fee";
	}

	// 오시는길
	@GetMapping("/map")
	public String mapPage() {
		return "/explicateList/map";
	}

	// 별솔소식 리스트

	// 이벤트
	@GetMapping("/event")
	public String eventPage() {
		return "/newsList/event";
	}

	// 회원서비스 리스트
	// 객실예약
	@GetMapping("/leftover")
	public String leftoverPage() {
		return "redirect:/reserv/addReserv";
	}

	// 객실현황
	@GetMapping("/guestroom")
	public String guestroomPage(HttpSession session , Model m) {
		m.addAttribute("roomList",roomService.getRoomAll());
		return "/serviceList/guestroom";
	}

	// 후기게시판
	@GetMapping("/board")
	public String boardPage() {
		return "/serviceList/board";
	}

	// 주변관광지 리스트
	// 여행코스
	@GetMapping("/trip")
	public String tripPage() {
		return "/tour/trip";
	}

	// 골프코스
	@GetMapping("/golf")
	public String golfPage() {
		return "/tour/golf";
	}

	// 등산코스
	@GetMapping("/mount")
	public String mountPage() {
		return "/tour/mount";
	}

	// 하단메뉴
	// 회사소개
	@GetMapping("/about")
	public String aboutPage() {
		return "/bottom/about";
	}

	// 개인정보 처리방침
	@GetMapping("/privacy")
	public String privacyPage() {
		return "/bottom/privacy";
	}

	// 영상정보처리기기
	@GetMapping("/operation")
	public String operationPage() {
		return "/bottom/operation";
	}

	// 이용약관
	@GetMapping("termsofuse")
	public String termsofusePage() {
		return "/bottom/termsofuse";
	}

	// 이메일무단수집거부
	@GetMapping("/emailcollection")
	public String emailcollectionPage() {
		return "/bottom/emailcollection";
	}

	// 이메일 인증
	@GetMapping("/emailCer")
	public String emailCertificationPage() {
		return "redirect:/cus/mailCheck";
	}

	@GetMapping("/imgUpdate")
	public String goTestForm(@RequestParam(defaultValue = DEFAULT_CLASSIFICATION)String classification, @RequestParam(defaultValue = DEFAULT_VALUE)String value, Model m, HttpSession session , @RequestParam(defaultValue = "")String errorMessage) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if(!classification.equals(DEFAULT_CLASSIFICATION) && !value.equals(DEFAULT_VALUE)) {
				if(!errorMessage.equals(""))
					m.addAttribute("errorMessage",errorMessage);
				m.addAttribute("classification", classification);
				m.addAttribute("value", value);
				List<String> imgList = ftpService.ftpImgPath(classification,value);
				if(imgList.size()>0) {
				m.addAttribute("imgList", imgList);
				return "/adminPage/adminUpdateImg";
				}else {
					m.addAttribute("errorMessage","잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
				}else {
					m.addAttribute("errorMessage","잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
			return "redirect:/index/main";
		}
	}

	@Autowired
	FtpService ftpService;

	@PostMapping("/imgUpdate")
	public String test(@RequestParam(required = false) MultipartFile uploadFile, @RequestParam(defaultValue = DEFAULT_VALUE)String value, @RequestParam(defaultValue = DEFAULT_CLASSIFICATION)String classification,
			Model m, HttpSession session, @RequestParam(required = false, defaultValue = "") String dumpImg) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if(!value.equals(DEFAULT_VALUE) && !classification.equals(DEFAULT_CLASSIFICATION)){
				if (!uploadFile.isEmpty() || !dumpImg.equals("")) {
					if (!uploadFile.isEmpty() && dumpImg.equals("") && ftpService.fileTypeCheck(uploadFile)) {
						ftpService.ftpAdminImg(uploadFile, classification, value);
						m.addAttribute("value",value);
						return "redirect:"+indexService.returnPath(classification, value);
					} else if (uploadFile.isEmpty() && !dumpImg.equals("") ) {
						System.out.println(value);
						System.out.println(dumpImg);
						String dumpImgOnlyName = dumpImg.substring(dumpImg.lastIndexOf('/')+1,dumpImg.lastIndexOf('.'));
						System.out.println(dumpImgOnlyName);
						if(dumpImgOnlyName.equals(value)) {
							System.out.println("yes");
						}else {
						ftpService.ftpAdminImgRename(classification, value, dumpImg);
						}
						return "redirect:"+indexService.returnPath(classification, value);
					}else {
						System.out.println(dumpImg);
						m.addAttribute("classification",classification);
						m.addAttribute("value",value);
						return "redirect:/index/imgUpdate";
					}
				} else {
					m.addAttribute("errorMessage","파일이 아무것도 선택 되지 않았습니다.");
					return "redirect:/index/imgUpdate?value="+value+"&classification="+classification;
				}
				}else {
					m.addAttribute("errorMessage","잘못된 접근 입니다.");
					return "redirect:/index/main";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
			return "redirect:/index/main";
		}
	}
	

}
