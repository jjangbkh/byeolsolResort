package model.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dto.Board;
import model.dto.Customer;
import model.dto.Question;
import model.dto.Remove;
import model.dto.Reserv;
import model.mapper.AnswerMapper;
import model.mapper.BoardMapper;
import model.mapper.CommentMapper;
import model.mapper.CustomerMapper;
import model.mapper.QuestionMapper;
import model.mapper.RemoveMapper;
import model.mapper.ReservMapper;
import model.view.CustomerView;

@Service("customerService")
public class CustomerService {

	private static final int CUSTOMER_COUNT_PER_PAGE = 10;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	BoardService boardService;

	@Autowired
	CommentMapper commentMapper;

	@Autowired
	RemoveMapper removeMapper;

	@Autowired
	ReservMapper reservMapper;

	@Autowired
	AnswerMapper answerMapper;

	@Autowired
	QuestionMapper questionMapper;

	// customerVo 로 인해 유효성 검사가 끝났기 때문에 바로 db에 저장
	public void register(Customer customer) {
		System.out.println("회원가입 넘어 오는지 확인 : " + customer);
		customerMapper.insertCustomer(customer);
	}

	// userId 와 password가 맞는 customer를 가져옴
	public Customer logIn(String userId, String password) {
		System.out.println("로그인 넘어 오는지 확인 : " + userId);
		Customer c = customerMapper.selectCustomer(userId, password);
		System.out.println("select 되는지 확인" + c);
		return c;
	}

	// userId customer db에 있는 지 확인 후 결과값 반환
	public String idCheck(String userId) {
		// pattern 과 match 되는지 결과 확인후 결과 값 반환
		String pattern = "[a-zA-Z]{2}+[a-z0-9]{3,10}";
		boolean result = Pattern.matches(pattern, userId);
		
		if(!userId.contains("admin") && result) {
		Customer c = customerMapper.selectCustomerWithId(userId);
		if (c != null) {
			return "중복";
		} else
			return "중복 아님";
		}else {
			return "사용 불가";
		}
	}

	// 커스터머 있는지 확인
	public Customer check(Customer customer) {
		System.out.println("있는지 확인 : " + customer);
		return customerMapper.selectCustomer(customer.getUserId(), customer.getPassword());
	}

	// email을 받아와서 customerdb에있는지 확인하기
	public String emailCheck(String email) {
		Customer c = customerMapper.selectCustomerWithEmail(email);
		if (c == null) {
			return "중복 아님";
		} else {
			return "중복";
		}
	}

	// email 이랑 emailCode로 찾아옴
	public Customer getCustomerByEmail(String email, String emailCode) {
		return customerMapper.selectCustomerByEmailAndEmailState(email, emailCode);
	}

	// userId를 갖고 customer를 찾아와서 반환
	public Customer getCustomerById(String userId) {
		return customerMapper.selectCustomerWithId(userId);
	}

	
	// 메일 보내기
	public void mailSend(JavaMailSender mailSender, String registEmail) {
		// 이메일에 보낼 랜덤 4자리코드
		String mailCode = (int) (Math.random() * 8999) + 1000 + "";
		// 관리자 이메일
		String setfrom = "byeolsol6@gmail.com";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// message를 보내는데 utf-8
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(registEmail); // 받는사람 이메일
			messageHelper.setSubject("별솔리조트 인증번호 입니다."); // 메일제목은 생략이 가능하며 , html을 설정
			messageHelper.setText(
					"<div style='margin: 0auto; width: 800px; border-bottom: 3px solid lightgray; border-top: 3px solid lightgray; text-align: center'>"
							+ "<span> 별솔리조트 인증번호 입니다. 별솔리조트 이메일 인증 화면에 인증 코드를 입력 하여 주세요" + "</span><h3>" + mailCode
							+ "</h3><span>" + "이메일 인증을 하지 않으시면 별솔리조트 서비스중 일부 서비스를 이용 하실 수 없습니다." + "</span></div>",
					true); // 메일 내용
			mailSender.send(message);
			customerMapper.updateStateByEmail(registEmail, mailCode);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// customer의 email state를 변경함
	public void updateCustomerEmailState(String userEmail, String emailState) {
		customerMapper.updateStateByEmail(userEmail, emailState);
	}

	// phone을 받아와서 customer db에 있는지 확인후 리턴
	public String phoneCheck(String phone) {

		Customer customer = customerMapper.selectCustomerByPhone(phone);
		if (customer == null)
			return "중복 아님";
		else
			return "중복";
	}

	// customer 업데이트
	public void updateCustomer(Customer cust) {
		customerMapper.updateCustomer(cust);
	}

	@Transactional
	// customer의 계정탈퇴를 할경우 삭제하는 것
	public void deleteCustomerWithAllInfor(Customer customer) {
		// userId가 받은 customer의 아이디를 받아와서 그 아이디를가진 유저가 쓴 게시글의 리스트를 삭제 하고
		List<Board> boardList = boardService.selectBoardListByUserId(customer.getUserId());

		for (Board board : boardList) {
			boardService.deleteBoard(board.getId(), customer.getUserId());
		}
		// 게시글과 comment를 삭제
		commentMapper.deleteCommentByUserId(customer.getUserId());

		// 예약 찾기
		List<Reserv> reservList = reservMapper.selectReservByUserId(customer.getUserId());

		for (Reserv reserv : reservList) {
			// 만약 예약이 입금된 상태이면 remove를 추가
			if (reserv.getState().equals("입금")) {
				removeMapper.insertRemove(new Remove(0, customer.getUserId(), reserv.getRoomId(), reserv.getStartDate(),
						reserv.getEndDate(), reserv.getTotalPrice() - (int) (reserv.getTotalPrice() / 10),
						customer.getName(), customer.getPhone(), null));
			}
			reservMapper.deleteReserv(reserv.getId());
		}
		// question과 asnwer를 삭제
		List<Question> questionList = questionMapper.selectQuestionByWriter(customer.getUserId());
		for (Question question : questionList) {
			answerMapper.deleteAnswerByQuestionId(question.getId());
			questionMapper.deleteQuestion(question.getId());
		}
		answerMapper.deleteAnswerByWriter(customer.getUserId());
		customerMapper.deleteCustomerById(customer.getId());

	}

	public CustomerView getCustomerView(int pageNum) {
		CustomerView customerView = null;
		int firstRow = 0;
		List<Customer> customerList = null;
		int customerCnt = customerMapper.countCustomer();
		if (customerCnt > 0) {
			firstRow = (pageNum - 1) * CUSTOMER_COUNT_PER_PAGE;
			customerList = customerMapper.selectCustomerListWithLimit(firstRow, CUSTOMER_COUNT_PER_PAGE);
		} else {
			pageNum = 0;
		}
		customerView = new CustomerView(customerCnt, pageNum, firstRow, CUSTOMER_COUNT_PER_PAGE, customerList);
		return customerView;
	}
	
	// userId와 이름을 받아서 일치하면 가입한 메일로 임시 비밀번호 보내기
	public String mailSendByPassword(JavaMailSender mailSender, String email, String userId, String name) {
		if (isCheck(email, userId, name)) {
			// 임시 비밀번호
			String temporaryPassword = randomTemporaryPassword();
			String setfrom = "byeolsol6@gmail.com";
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(email); // 받는사람 이메일
				messageHelper.setSubject("별솔리조트 비밀 번호 조회 결과 입니다.."); // 메일제목은 생략이 가능하다
				messageHelper.setText(
						"<div style='margin: 0auto; width: 800px; border-bottom: 3px solid lightgray; border-top: 3px solid lightgray; text-align: center'>"
								+ "<span> 별솔리조트 임시 비밀 번호 입니다. 로그인 시 이 임시 비밀 번호를 입력 하여 주세요" + "</span><h3>"
								+ temporaryPassword + "</h3><span>" + "임시 비밀번호로 로그인 후 마이페이지 에서 비밀 번호 변경이 가능 합니다."
								+ "</span></div>",
						true); // 메일 내용
				mailSender.send(message);
				Customer customer = customerMapper.selectCustomerWithId(userId);
				customer.setPassword(temporaryPassword);
				customerMapper.updateCustomer(customer);
				return "이메일로 임시 비밀번호를 보냈습니다. 확인하여 주세요";
			} catch (Exception e) {
				System.out.println(e);
				return "메일 전송 오류 입니다.";
			}
		} else {
			return "잘못된 정보 입니다. 확인 해 주세요";
		}
	}
	
	// 가입한 메일로 아이디 보냄
	public String mailSendWithId(JavaMailSender mailSender, String email, String name) {
		System.out.println(email+","+name);
		Customer customer = customerMapper.selectCustomerWithEmail(email);
		System.out.println(customer);
		if (customer != null) {
			if (customer.getName().equals(name)) {
				String setfrom = "byeolsol6@gmail.com";
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
					messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
					messageHelper.setTo(customer.getEmail()); // 받는사람 이메일
					messageHelper.setSubject("별솔리조트 아이디 조회 결과 입니다."); // 메일제목은 생략이 가능하다
					messageHelper.setText(
							"<div style='margin: 0auto; width: 800px; border-bottom: 3px solid lightgray; border-top: 3px solid lightgray; text-align: center'>"
									+ "<span> 별솔 리조트 아이디 찾기 요청으로 인해 해당 메일에 ID를 보냈으니 확인 후 별솔 리조트에 로그인 해주세요" + "</span><h3>"
									+ customer.getUserId() + "</h3></div>",
							true); // 메일 내용
					mailSender.send(message);
					return "가입시 기입한 이메일로 메일을 보냈습니다. 확인 하여 주세요";
				} catch (Exception e) {
					System.out.println(e);
					return "메일 전송 오류 입니다.";
				}
			}else {
				return "정보를 다시 확인 하여 주세요";
			}
		}else {
			return "정보를 다시 확인 하여 주세요";
		}

	}
	// 이메일과 아이디,이름이 db와 일치하는지 확인
	public boolean isCheck(String email, String userId, String name) {
		Customer customer = customerMapper.selectCustomerWithId(userId);
		if (customer != null) {
			if (customer.getEmail().equals(email) && customer.getName().equals(name)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	// random임시 비밀번호
	private String randomTemporaryPassword() {

		String temporaryPassword = "";
		for (int i = 0; i < 8; i++) {

			if (i % 2 == 0) {
				temporaryPassword += (char) ('a' + (int) (Math.random() * 25) + 1) + "";
			} else {
				temporaryPassword += (int) (Math.random() * 9) + 1;
			}
		}

		return temporaryPassword;

	}

}
