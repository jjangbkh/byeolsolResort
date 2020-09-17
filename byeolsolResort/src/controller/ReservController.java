package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dto.Customer;
import model.dto.ErrorMessage;
import model.dto.Reserv;
import model.dto.Room;
import model.mapper.ReservMapper;
import model.service.CustomerService;
import model.service.RemoveService;
import model.service.ReservService;
import model.service.RoomService;

@Controller
@RequestMapping("/reserv")
public class ReservController {

	@Autowired
	ReservService reservService;

	@Autowired
	CustomerService customerService;

	@Autowired
	RemoveService removeService;
	
	@GetMapping("/addReserv")
	public String goAddreservForm(HttpSession session, Model m, @RequestParam(defaultValue = "101") int roomNum , @RequestParam(defaultValue = DEFAULT_START_DATE)Date startDate , @RequestParam(defaultValue = DEFAULT_END_DATE)Date endDate) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			Customer customer = customerService.getCustomerById(userId);
			if (customer.getEmailState().equals("인증")) {
				if (roomNum >= 101 && roomNum <= 105 || roomNum >= 201 && roomNum <= 205
						|| roomNum >= 301 && roomNum <= 305) {
					m.addAttribute("roomNum", roomNum);
				}
				if(!startDate.toString().equals(DEFAULT_START_DATE) && ! endDate.toString().equals(DEFAULT_END_DATE)) {
					m.addAttribute("startDate",startDate);
					m.addAttribute("endDate",endDate);
				}
				return "/serviceList/leftover";
			} else {
				m.addAttribute("errorMessage", "이메일 인증이 되어 있지 않은 계정입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping("/addReserv")
	public String addReservResult(HttpSession session, Model m,
			@RequestParam(defaultValue = DEFAULT_START_DATE) Date startDate,
			@RequestParam(defaultValue = DEFAULT_END_DATE) Date endDate, @RequestParam(defaultValue = "0") int roomNum,
			@RequestParam(defaultValue = "0") int peopleCount) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			Customer customer = customerService.getCustomerById(userId);
			if (customer.getEmailState().equals("인증")) {
				if (!startDate.toString().equals(DEFAULT_START_DATE) && !endDate.toString().equals(DEFAULT_END_DATE)
						&& roomNum != 0 && peopleCount != 0) {
					ErrorMessage errorMessage = reservService.totalAddResrv(startDate, endDate, roomNum, session,
							peopleCount);
					if (errorMessage.getErrorMessage() != null) {
						m.addAttribute("errorMessage", errorMessage.getErrorMessage());
					}
					if (errorMessage.getRoomNum() > 0) {
						m.addAttribute("roomNum", errorMessage.getRoomNum());
					}
					return errorMessage.getResult();
				} else {
					m.addAttribute("errorMessage", "예약 정보를 다시 확인 해 주세요");
					return "redirect:/reserv/addReserv";

				}

			} else {
				m.addAttribute("errorMessage", "이메일 인증이 되어 있지 않은 계정입니다.");
				return "redirect:/index/main";
			}

		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@GetMapping("/printTotalPrice")
	public String errorTotalPrice(Model m) {
		m.addAttribute("잘못된 접근 입니다.");
		return "redirect:/index/main";
	}

	@PostMapping("/printTotalPrice")
	@ResponseBody
	public String getTotalPrice(@RequestParam(defaultValue = DEFAULT_START_DATE) Date startDate,
			@RequestParam(defaultValue = DEFAULT_END_DATE) Date endDate,
			@RequestParam(defaultValue = "0") int roomNum) {
		if (!startDate.equals(DEFAULT_START_DATE) && !endDate.equals(DEFAULT_END_DATE) && roomNum != 0) {
			LocalDate start = startDate.toLocalDate();
			LocalDate end = endDate.toLocalDate();
			Room room = roomService.getRoomByRoomNum(roomNum);
			if (reservService.dateCheck(start, end)) {
				int totalPrice = reservService.getTotalPrice(start, end, room);
				return totalPrice + "";
			} else {
				return "0";
			}
		} else {
			return "0";
		}

	}

	private final String DEFAULT_START_DATE = "1980-08-08";
	private final String DEFAULT_END_DATE = "1980-08-08";

	@Autowired
	RoomService roomService;

	@GetMapping("/updateReserv")
	public String updateReservForm(HttpSession session, Model m, @RequestParam(defaultValue = "0") int reservId) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (reservId != 0) {
				Reserv reserv = reservService.getReservById(reservId);
				if (reserv != null) {
					if (reserv.getUserId().equals(userId) || userId.equals("admin")) {
						m.addAttribute("reserv", reserv);
						m.addAttribute("roomNum", roomService.getRoomById(reserv.getRoomId()).getRoomNum());
						return "/mypage/updateReserv";
					} else {
						m.addAttribute("errorMessage", "권한이 없습니다.");
						return "redirect:/index/main";
					}
				} else {
					m.addAttribute("errorMessage", "잘못된 접근 입니다.");
					return "redirect:/cus/myReserv";
				}
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/cus/myReserv";
			}

		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/updateReserv")
	public String updateReserv(@RequestParam(defaultValue = "0") int roomNum,
			@RequestParam(defaultValue = DEFAULT_START_DATE) Date startDate,
			@RequestParam(defaultValue = DEFAULT_END_DATE) Date endDate,
			@RequestParam(defaultValue = "0") int peopleCount, HttpSession session,
			@RequestParam(defaultValue = "0") int reservId, Model m) {
		if (reservService.roomNumCheck(roomNum) && !startDate.toString().equals(DEFAULT_START_DATE)
				&& !endDate.toString().equals(DEFAULT_END_DATE)) {
			ErrorMessage errorMessage = reservService.updateReserv(session, reservId, startDate, endDate, roomNum,
					peopleCount);
			if (errorMessage.getErrorMessage() != null) {
				System.out.println(errorMessage.getErrorMessage());
				m.addAttribute("errorMessage", errorMessage.getErrorMessage());
			}
			if (errorMessage.getRoomNum() > 0) {
				m.addAttribute("roomNum", errorMessage.getRoomNum());
			}
			return errorMessage.getResult();
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다");
			return "redirect:/reserv/updateReserv";
		}
	}

	@GetMapping("/deleteReserv")
	public String deleteReserv(HttpSession session, @RequestParam(defaultValue = "0") int reservId, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (reservId != 0) {
				Reserv reserv = reservService.getReservById(reservId);
				if (reserv.getUserId().equals(userId) || userId.equals("admin")) {
					reservService.deleteReserv(reservId);
					if(!userId.equals("admin")) {
					return "redirect:/cus/myReserv";
					}else {
						return "redirect:/index/adminRoom";
					}
				} else {
					m.addAttribute("errorMessage", "권한이 없습니다.");
					return "redirect:/index/main";
				}
			} else {
				if(!userId.equals("admin")) {
				m.addAttribute("errorMessage", "예약 정보를 다시 확인 해주세요");
				return "redirect:/cus/myReserv";
				}else {
					m.addAttribute("errorMessage","예약 정보를 다시 확인 해주세요");
					return "redirect:/index/adminRoom";
				}
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/adminReservAdd")
	public String adminReservAddForm(HttpSession session, Model m) {

		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				return "adminReservAddForm";
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping("/printRoomNum")
	@ResponseBody
	public List<Room> getRoomNum(Date startDate, Date endDate) {
		return reservService.getReservCheckNoRoomId(startDate.toLocalDate(), endDate.toLocalDate());
	}

	@GetMapping("/updateReservState")
	public String adminUpdateReservState(@RequestParam(defaultValue = "0")int id, HttpSession session, Model m) {
		System.out.println(session.getAttribute("userId")!=null);
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if(id>0) {
				reservService.updateReservState(id);
				System.out.println(userId);
				return "redirect:/index/adminRoom";
				}else {
					m.addAttribute("errorMessage","잘못된 접근 입니다.");
					return"";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/adminReservPage")
	public String adminReservPage(HttpSession session, Model m, @RequestParam(defaultValue = "1") int pageNum) {
		System.out.println(session.getAttribute("userId"));
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				m.addAttribute("reservInfoView", reservService.getAdminReservInfoView(pageNum));
				return "/adminPage/adminRoom";
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@GetMapping("/adminRemovePage")
	public String adminRemovePage(HttpSession session, Model m, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "")String errorMessage) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if(!errorMessage.equals("")) {
					m.addAttribute("errorMessage",errorMessage);
				}
				m.addAttribute("removeView", removeService.getRemoveView(pageNum));
				return "/adminPage/removePage";
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
		
	}

	@GetMapping("/adminDeleteRemove")
	public String adminDeleteRemove(HttpSession session, Model m, @RequestParam(defaultValue = "0") int id) {

		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if (id > 0) {
					removeService.removeRemove(id);
					return "redirect:/reserv/adminRemovePage";
				} else {
					m.addAttribute("errorMessage","잘못된 정보 입니다.");
					return "redirect:/reserv/adminRemovePage";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

}
