package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.dto.Customer;
import model.dto.CustomerVO;
import model.service.BoardService;
import model.service.CustomerService;
import model.service.HolyDayService;
import model.service.ReservService;
import model.view.CustomerView;

@Controller
@RequestMapping("/cus")
public class CustomerController {

	// default value들
	private static final String DEFAULT_VALUE_ID = "defaultValueId";
	private static final String DEFAULT_VALUE_PASSWORD = "defaultValuePassword";
	private static final String DEFAULT_VALUE_Email = "defaultValueEmail@test.com";
	private static final String DEFAULT_BIRTH_DATE = "1850-01-01";

	@Autowired
	CustomerService customerService;

	@Autowired
	private Validator validator;

	@GetMapping("/regis")
	public String getRegisForm(HttpSession session, Model m, @RequestParam(defaultValue = "") String errorMessage) {
		if (session.getAttribute("userId") == null) {
			// session에 userId가 null이라면
			if (!errorMessage.equals(""))
				m.addAttribute("errorMessage", errorMessage);
			return "/sideform/signup";
		} else {
			// session에 userId가 null이 아니라면
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/regis")
	// CustomerVo를 통해 유효성 검사를 함 + birth를 받는데 없다면 deleteValue로 설정
	public String getResultRegis(HttpServletRequest resq, @ModelAttribute("CustomerVo") @Valid CustomerVO customerVo,
			BindingResult result, @RequestParam(defaultValue = DEFAULT_BIRTH_DATE) Date birth, Model m, @RequestParam(defaultValue = "")String check1,
			@RequestParam(defaultValue = "")String check2,@RequestParam(defaultValue = "")String check3,
			HttpSession session) {
		if (session.getAttribute("userId") == null) {
			customerVo.setId(0);
			// birth의 값이 defaultValue인지 확인 후 아니라면 birth를 localDate로 바꿈
			if (!birth.toString().equals(DEFAULT_BIRTH_DATE)) {
				customerVo.setBirthDate(birth.toLocalDate());

				System.out.println(customerVo);

				// 유효성 검사를 해서 error가 나는 지 확인후 fieldError를 이용해 에러가 난 필드 앞에 e를 추가 하고 page로 이동
				if (result.hasErrors()) {
					List<FieldError> errors = result.getFieldErrors();
					for (FieldError fe : errors) {
						m.addAttribute("e" + fe.getField(), fe.getField());
						System.out.println(fe);
					}
					return "/sideform/signup";
				} else {

					// id가 중복인지 확인후
					String idCheck = customerService.idCheck(customerVo.getUserId());
					if (idCheck.equals("중복")) {
						m.addAttribute("errorMessage", "id가 중복되었습니다.");
						return "redirect/cus/regis";

					} else { // email이 중복인지 아닌지 확인
						String emailCheck = customerService.emailCheck(customerVo.getEmail());
						if (!emailCheck.equals("중복")) { // 중복이 아니라면
							if (!customerService.phoneCheck(customerVo.getPhone()).equals("중복")) { // 전화번호 중복 체크
								if (LocalDate.now().getYear() - birth.toLocalDate().getYear() >= 5) { // 입력한 생일이 현재 날짜와
																										// 비교후 5미만이라면
																										// return false
									if(!check1.equals("") && !check2.equals("") && !check3.equals("")) {
									Customer customer = new Customer(customerVo.getId(), customerVo.getUserId(),
											customerVo.getPassword(), customerVo.getName(), customerVo.getZipCode(),
											customerVo.getEmail(), customerVo.getAddress(),
											customerVo.getAddressDetail(), customerVo.getPhone(),
											customerVo.getEmailState(), customerVo.getBirthDate());
									customerService.register(customer);
									m.addAttribute("registEmail", customer.getEmail());
									return "redirect:/cus/mailCheck";
									}else {
										m.addAttribute("errorMessage","이용약관을 모두 선택 해주셔야 회원가입을 하실 수 있습니다.");
										return"redirect:/cus/regis";
									}
								} else {
									System.out.println(LocalDate.now().compareTo(birth.toLocalDate()));
									m.addAttribute("errorMessage", "생년월일을 확인 해주세요");
									return "redirect:/cus/regis";
								}
							} else {
								m.addAttribute("errorMessage", "전화번호가 중복되었습니다.");
								return "redirect:/cus/regis";
							}
						} else {
							m.addAttribute("errorMessage", "email이 중복 되었습니다.");
							return "redirect:/cus/regis";
						}
					}
				}
			} else {
				m.addAttribute("errorMessage", "생년월일을 다시 확인 해주세요");
				return "redirect:/cus/regis";
			}

		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping(value = "/idcheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String getIdCheck(String userId) {
		return customerService.idCheck(userId);
	}

	@PostMapping(value = "/eamilcheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	// 바로 그 페이지에 return 해줌
	public String getEmailCheck(String email) {
		System.out.println(email);
		// email이 중복인지 아닌지 확인
		return customerService.emailCheck(email);
	}

	@PostMapping(value = "/phoneCheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	// 중복인지 아닌지 확인
	public String getPhoneCheck(String phone, HttpSession session) {
		if(session.getAttribute("userId")==null) {
		return customerService.phoneCheck(phone);
		}else {
			String userId = (String)session.getAttribute("userId");
			Customer customer = customerService.getCustomerById(userId);
			if(customer.getPhone().equals(phone)) {
				System.out.println("중복아님");
				return "중복 아님";
			}else {
				return customerService.phoneCheck(phone);
			}
			
		}
	}

	@GetMapping("/login")
	public String loginForm(HttpSession session, Model m, @RequestParam(required = false) String errorMessage) {
		// 로그인이 되어있지 않다면
		if (session.getAttribute("userId") == null) {
			if (errorMessage != null) {
				m.addAttribute("errorMessage", errorMessage);
			}
			return "/sideform/login";
		} else {
			m.addAttribute("errorMessage", "잘못된 접근");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam(defaultValue = DEFAULT_VALUE_ID) String userId,
			@RequestParam(defaultValue = DEFAULT_VALUE_PASSWORD) String password, HttpSession session, Model m) {
		if (!userId.equals(DEFAULT_VALUE_ID) || !password.equals(DEFAULT_VALUE_PASSWORD)) {
			Customer c = customerService.logIn(userId, password);
			if (c == null) {
				m.addAttribute("errorMessage", "id 또는 비밀번호가 잘못 되었습니다.");
				return "redirect:/cus/login";
			} else {

				session.setAttribute("userId", c.getUserId());
				session.setAttribute("userName", c.getName());
				session.setAttribute("state", c.getEmailState());
				session.setAttribute("userEmail", c.getEmail());
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "id 또는 비밀번호를 확인해 주세요");
			return "redirect:/cus/login";
		}
	}

	@GetMapping("/logout")
	public String logOut(HttpSession session, Model m) {
		if (session.getAttribute("userId") != null) {
			System.out.println(session.getAttribute("userId"));
			System.out.println(session.getAttribute("userName"));
			session.setAttribute("userId", null);
			session.setAttribute("userName", null);
			session.setAttribute("userEmail", null);
			session.setAttribute("state", null);
			return "redirect:/index/main";
		} else {
			m.addAttribute("errorMessage", "잘못된 접근");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/myPage")
	public String goMyPage(HttpSession session, Model m, @RequestParam(defaultValue = "") String errorMessage) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (!userId.equals("admin")) {
				Customer customer = customerService.getCustomerById(userId);
				if (!errorMessage.equals(""))
					m.addAttribute("errorMessage", errorMessage);
				m.addAttribute("customer", customer);
				return "/mypage/mypage";
			} else {
				return "redirect:/cus/adminUserInfo";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@PostMapping("/updateCustomer")
	public String updateCustomer(HttpSession session, Model m,
			@ModelAttribute("CustomerVo") @Valid CustomerVO customerVo, BindingResult result,
			@RequestParam(defaultValue = DEFAULT_BIRTH_DATE) Date birth) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			Customer cust = new Customer();
			if (customerVo.getUserId().equals(userId)) { // customerVo로 유효성 검사
				if (!birth.toString().equals(DEFAULT_BIRTH_DATE)) {
					Customer customer = customerService.getCustomerById(userId);
					cust.setId(customer.getId());
					cust.setUserId(userId);
					if (result.hasErrors()) {
						List<FieldError> errors = result.getFieldErrors();
						for (FieldError fe : errors) {
							m.addAttribute("e" + fe.getField(), fe.getField());
							System.out.println(fe);
						}
						return "redirect:/cus/myPage";
					} else {
						if (customerService.phoneCheck(customerVo.getPhone()).equals("중복")) {
							if (customer.getPhone().equals(customerVo.getPhone())) {
								cust.setPhone(customerVo.getPhone());
							}else {
								cust.setPhone(customer.getPhone());
							}
						} else {
							cust.setPhone(customerVo.getPhone());
						}
						if(customerService.emailCheck(customerVo.getEmail()).equals("중복")) {
							if(customer.getEmail().equals(customerVo.getEmail())) {
								cust.setEmail(customerVo.getEmail());
							}else {
								cust.setEmail(customer.getEmail());
							}
						}else {
							cust.setEmail(customerVo.getEmail());
						}
						
						
						if (LocalDate.now().getYear() - birth.toLocalDate().getYear() >= 5) {
							cust.setBirthDate(birth.toLocalDate());
						}
						cust.setAddress(customerVo.getAddress());
						cust.setAddressDetail(customerVo.getAddressDetail());
						cust.setEmailState(customer.getEmailState());
						cust.setName(customerVo.getName());
						cust.setPassword(customerVo.getPassword());
						cust.setZipCode(customerVo.getZipCode());
						System.out.println(cust.getPhone());
						System.out.println(cust);
						cust.setId(customer.getId());
						customerService.updateCustomer(cust);
						return "redirect:/cus/myPage";
					}
				} else {
					m.addAttribute("errorMessage", "생일을 다시 확인 하여 주세요");
					return "redirect:/cus/myPage";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없습니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping(value = "/mailCheck", produces = "application/text; charset=utf-8")
	public String mailSending(@RequestParam(required = false) String registEmail, HttpSession session, Model m) {

		// 회원가입 후 넘어온 이메일이 null이 아니라면
		if (registEmail != null) {
			m.addAttribute("userEmail", registEmail);
			return "/mypage/emailCer";
			// userEmail이 null이 아니라면
		} else if (session.getAttribute("userEmail") != null) {
			String userId = (String) session.getAttribute("userId");
			Customer customer = customerService.getCustomerById(userId);
			if (!customer.getEmailState().equals("인증")) {
				customerService.updateCustomerEmailState(customer.getEmail(), "미인증");
				return "/mypage/emailCer";
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping(value = "/mailSend", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String mailSendRes(@RequestParam(defaultValue = DEFAULT_VALUE_Email) String userEmail, HttpSession session) {
		if (!userEmail.equals(DEFAULT_VALUE_Email)) {
			// 이메일 보내기
			customerService.mailSend(mailSender, userEmail);
			return "이메일 인증코드를 성공적으로 보냈습니다.";
		} else {
			return "잘못된 접근";
		}
	}

	@PostMapping("/mailCheck")
	// 이메일 인증 코드와 입력한 코드가 같다면 state를 인증으로 인증코드와 같지 않다면 state를 미인증으로
	public String emailCertification(@RequestParam(defaultValue = "DEFAULTMAILCODE") String mailCode,
			@RequestParam(defaultValue = DEFAULT_VALUE_Email) String userEmail, Model m) {
		if (!mailCode.equals("DEFAULTMAILCODE") && !userEmail.equals(DEFAULT_VALUE_Email)) {
			Customer customer = customerService.getCustomerByEmail(userEmail, mailCode);
			if (customer != null) {
				customerService.updateCustomerEmailState(userEmail, "인증");
				m.addAttribute("successMessage", "이메일 인증이 완료 되었습니다. 로그인후 별솜 리조트 홈페이지 서비스를 이용하실 수 있습니다.");
				return "redirect:/index/main";
			} else {
				customerService.updateCustomerEmailState(userEmail, "미인증");
				m.addAttribute("errorMessage", "인증코드와 일치하지 않습니다.");
				return "redirect:/index/mailCer";
			}
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}

	}

	@Autowired
	ReservService reservService;

	@GetMapping("/myReserv")
	public String myReservPage(HttpSession session, @RequestParam(defaultValue = "1") int pageNum, Model m) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if(!userId.equals("admin")) {
			Customer customer = customerService.getCustomerById(userId);
			if (customer.getEmailState().equals("인증")) {
				m.addAttribute("reservWithRoomNumView", reservService.getReservWithRoomNumView(pageNum, userId));
				return "/mypage/myRoom";
			} else {
				m.addAttribute("errorMessage", "이메일 인증이 되어있지 않은 계정입니다.");
				return "redirect:/cus/myPage";
			}
			}else {
				m.addAttribute("errorMessage","잘못된 접근 입니다.");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomerForm(HttpSession session, Model m,
			@RequestParam(defaultValue = "") String errorMessage) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			Customer customer = customerService.getCustomerById(userId);
			if (customer != null && !userId.equals("admin")) {
				if (!errorMessage.equals(""))
					m.addAttribute("errorMessage", errorMessage);
				m.addAttribute("customer", customer);
				return "/mypage/deleteForm";
			} else {
				m.addAttribute("errorMessage","잘못된 접근 입니다.");
				return "/cus/myPage";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/deleteCustomer")
	// userId와 password를 확인하고 맞으면 계정삭제 -> 게시글 ,댓글 , reserv 등을 삭제
	public String deleteCustomer(HttpSession session, @RequestParam(defaultValue = DEFAULT_VALUE_ID) String userId,
			@RequestParam(defaultValue = DEFAULT_VALUE_PASSWORD) String password, Model m) {
		if (session.getAttribute("userId") != null) {
			if (!userId.equals(DEFAULT_VALUE_ID) || !password.equals(DEFAULT_VALUE_PASSWORD)) {
				String sessionUserId = (String) session.getAttribute("userId");
				Customer sessionCustoemr = customerService.getCustomerById(sessionUserId);
				if (sessionCustoemr.getPassword().equals(password)) {
					customerService.deleteCustomerWithAllInfor(sessionCustoemr);
					session.setAttribute("userId", null);
					session.setAttribute("userName", null);
					session.setAttribute("state", null);
					session.setAttribute("userEmail", null);
					m.addAttribute("deleteMessage", "계정을 탈퇴 하였습니다");
					return "redirect:/index/main";
				} else {
					m.addAttribute("errorMessage", "아이디 또는 비밀번호를 다시 확인 해 주세요");
					return "redirect:/cus/deleteCustomer";
				}
			} else {
				m.addAttribute("errorMessage", "비밀번호를 확인해 주세요");
				return "redirect:/cus/deleteCustomer";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/findId")
	public String findIdForm(HttpSession session, Model m, @RequestParam(defaultValue = "") String errorMessage) {
		if (session.getAttribute("userId") == null) {
			if (!errorMessage.equals(""))
				m.addAttribute("errorMessage", errorMessage);
			return "/mypage/findInfo";
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/findId")
	// email과 이름을 갖고 id찾기 하는데 email과 name이 맞는 customer를 찾아서 그 userId를 이메일로 보냄
	public String findId(HttpSession session, Model m, @RequestParam(defaultValue = DEFAULT_VALUE_Email) String email,
			@RequestParam(defaultValue = "DEFAULT_VALUE_NAME") String name) {
		if (session.getAttribute("userId") == null) {
			if (!email.equals(DEFAULT_VALUE_Email) && !name.equals("DEFAULT_VALUE_NAME")) {
				m.addAttribute("errorMessage", customerService.mailSendWithId(mailSender, email, name));
				return "redirect:/cus/login";
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/cus/findId";
			}
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/cus/login";
		}
	}

	@GetMapping("/findPassword")
	public String findPassword(HttpSession session, Model m) {
		if (session.getAttribute("userId") == null) {
			return "/mypage/findInfo";
		} else {
			m.addAttribute("errorMessage", "잘못된 접근 입니다.");
			return "redirect:/index/main";
		}
	}

	@PostMapping("/findPassword")
	// email, userId,name 등을 customerDb에서 확인
	public String findPassword(HttpSession session, Model m,
			@RequestParam(defaultValue = DEFAULT_VALUE_Email) String email,
			@RequestParam(defaultValue = DEFAULT_VALUE_ID) String userId,
			@RequestParam(defaultValue = "DEFAULT_VALUE_NAME") String name) {
		if (session.getAttribute("userId") == null) {
			if (!email.equals(DEFAULT_VALUE_Email) && !userId.equals(DEFAULT_VALUE_ID)
					&& !name.equals("DEFAULT_VALUE_NAME")) {
				m.addAttribute("errorMessage", customerService.mailSendByPassword(mailSender, email, userId, name));
				return "redirect:/cus/login";
			} else {
				m.addAttribute("errorMessage", "잘못된 접근 입니다.");
				return "redirect:/cus/findPassword";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}
	}

	@GetMapping("/adminUserInfo")
	public String goAdminUserInfo(HttpSession session, Model m, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String errorMessage) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if (!errorMessage.equals(""))
					m.addAttribute("errorMessage", errorMessage);
				CustomerView view  = customerService.getCustomerView(pageNum);
				for (Customer customer : view.getCustomerList()) {
					System.out.println(customer);
				}
				m.addAttribute("customerView", customerService.getCustomerView(pageNum));
				return "/adminPage/adminUser";
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다.");
				return "/redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "/redirect:/index/main";
		}
	}
}
