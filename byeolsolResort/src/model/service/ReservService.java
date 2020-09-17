package model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import model.dto.Customer;
import model.dto.ErrorMessage;
import model.dto.Remove;
import model.dto.Reserv;
import model.dto.ReservInfo;
import model.dto.ReservWithRoomNum;
import model.dto.Room;
import model.mapper.CustomerMapper;
import model.mapper.RemoveMapper;
import model.mapper.ReservMapper;
import model.view.ReservInfoView;
import model.view.ReservView;
import model.view.ReservWithRoomNumView;

@Service("reservService")
public class ReservService {

	private static final int RESERV_COUNT_PER_PAGE = 5;
	
	@Autowired
	ReservMapper reservMapper;
	
	@Autowired
	RemoveMapper removeMapper;

	// 해당날짜를 포함하는 roomId를 갖고 있는 예약을 가져와서 null이면 true 아니라면 false
	public boolean reservCheck(int roomId, LocalDate startDate, LocalDate endDate) {
		System.out.println(roomId);
		System.out.println(reservMapper.selectReservByRoomIdWithDate(roomId, startDate, endDate));
		// reserv가져오기
		if (reservMapper.selectReservByRoomIdWithDate(roomId, startDate, endDate).size()!=0) {
			return false;
		}
		else
			return true;
	}
	// 예약 update체크 위와 같지만 null이 아니면 reserv를 하나 가져와서 그 예약을 한사람이 본인이라면 업데이트
	public boolean reservUpdateCheck(int roomId, LocalDate startDate, LocalDate endDate, int reservId) {
		System.out.println("reservId : " + reservId);
		Reserv reserv = reservMapper.selectReservById(reservId);
		System.out.println(reserv);
		if(reserv != null) {
			System.out.println(reservMapper.selectReservByRoomIdWithDate(roomId, startDate, endDate));
			if(reservMapper.selectReservByRoomIdWithDate(roomId, startDate, endDate).size()==1&&reserv.getId() == reservMapper.selectReservByRoomIdWithDate(roomId, startDate, endDate).get(0).getId() || reservMapper.selectReservByRoomIdWithDate(roomId, startDate, endDate).size()<=0) {
				if(reserv.getStartDate().compareTo(LocalDate.now())>=1)
				return true;
				else return false;
			}else return false;
		}else {
			return false;
		}
	}
	

	public void addReserv(Reserv reserv) {
		reservMapper.insertReserv(reserv);
	}

	@Autowired
	CustomerService customerService;

	@Autowired
	RoomService roomService;

	// 예약을 추가하는 메서드 이지만 ErrorMessage를 return 함
	public ErrorMessage totalAddResrv(Date startDate, Date endDate, int roomNum, HttpSession session, int peopleCount) {
		if (session.getAttribute("userId") != null) {
			// room과 cstomer을 가져옴
			String userId = (String) session.getAttribute("userId");
			Customer customer = customerService.getCustomerById(userId);
			Room room = roomService.getRoomByRoomNum(roomNum);
			System.out.println(room);
			// email인증이 된 계정이라면 받은 date값을 localDate로
			if (customer.getEmailState().equals("인증")) {
				LocalDate start = startDate.toLocalDate();
				LocalDate end = endDate.toLocalDate();
				int dv = end.compareTo(start);
				// 퇴실일과 입실일이 같을 수는 있지만 적을순 없다
				if (dv >= 0) {
					// dateCheck 메서드 실행 후 결과가 true라면
					if (dateCheck(start, end)) {
						// room에 맞는 인원수 인지 확인 한다.
						if (peopleCountCheck(room, peopleCount)) {
							// 예약 금액을 갖는 메서드를 사용해 totalPrice에 넣음
							int totalPrice = getTotalPrice(start, end, room);
							// 예약 정보 넣기
							Reserv reserv = new Reserv(0, userId, room.getId(), start, end, totalPrice, peopleCount,
									null, null);
							if (reservCheck(room.getId(), start, end)) { // 예약 확인을 함
								System.out.println("확인함");
								// reserv를 추가
								addReserv(reserv);
								// errorMessage 와 redirect할 request를 넘겨줌 
								return new ErrorMessage("예약이 되었습니다. 관리자 계좌로 "+ (int)(reserv.getTotalPrice()/10)+"원을 선입금 해주셔야 예약이 완료 됩니다.", "redirect:/index/main", 0);
							} else {
								System.out.println("실패함");
								return new ErrorMessage("해당 날짜는 예약 하실수 없습니다.", "redirect:/reserv/addReserv", roomNum);
							}
						} else {
							return new ErrorMessage("해당 인원은 이 방을 예약 하실 수 없습니다. 해당 방을 예약하시려면 관리자에게 문의 해주세요",
									"redirect:/reserv/addreserv", roomNum);
						}
					} else {
						return new ErrorMessage("예약 가능 범위를 벗어났습니다.", "redirect:/reserv/addReserv", roomNum);
					}
				} else {
					return new ErrorMessage("종료날짜가 시작 날짜 이전일 수 없습니다.", "redirect:/reserv/addReserv", roomNum);
				}
			} else {
				return new ErrorMessage("이메일 인증이 되어있지 않은 계정입니다.", "redirect:/index/main", 0);
			}
		} else {
			return new ErrorMessage("로그인이 되어있지 않습니다.", "redirect:/index/main", 0);
		}
	}

	// errorMessage 자바의 속성인 errorMessage가 null이면 true반환
	public boolean isNullFromErrorMessage(ErrorMessage errorMessage) {
		if (errorMessage.getErrorMessage() == null) {
			return true;
		} else
			return false;
	}
	
	// ErrorMessage 안 속성 roomNum이 0 보다 작거나 같으면 return true
	public boolean isHaveRoomNumber(ErrorMessage errorMessage) {
		if (errorMessage.getRoomNum() <= 0)
			return true;
		else
			return false;
	}

	public Reserv getReservById(int reservId) {
		return reservMapper.selectReservById(reservId);
	}
	
	// 예약을 수정 하는 ErrorMessage return 하는 메서드 totalAddReserv와 같지만 check를 updateCheck로
	public ErrorMessage updateReserv(HttpSession session, int reservId, Date startDate, Date endDate, int roomNum,
			int peopleCount) {
		System.out.println(startDate);
		System.out.println(endDate);
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			Room room = roomService.getRoomByRoomNum(roomNum);
			System.out.println(room);
			if (peopleCountCheck(room, peopleCount)) {
				System.out.println("after peopleCount");
				if (dateCheck(startDate.toLocalDate(), endDate.toLocalDate())) {
					System.out.println("after dateCheck");
					if(reservUpdateCheck(room.getId(), startDate.toLocalDate(), endDate.toLocalDate(),reservId)) {
						System.out.println("after updateCheck");
						int total = getTotalPrice(startDate.toLocalDate(), endDate.toLocalDate(), room);
						Reserv reserv = new 
								Reserv(reservId, userId, room.getId(), startDate.toLocalDate(), endDate.toLocalDate(), total, peopleCount, null, null);
						reservMapper.updateReserv(reserv);
						return new ErrorMessage(null, "redirect:/cus/myReserv", 0);
					}else {
						return new ErrorMessage("예약 불가능한 날짜 입니다.", "redirect:/reserv/updateReserv?reservId="+reservId, roomNum);
					}
				} else {
					return new ErrorMessage("예약 가능 범위를 벗어났습니다.", "redirect:/reserv/updateReserv?reservId="+reservId, roomNum);
				}
			} else {
				return new ErrorMessage("해당 인원은 이 방을 예약 하실 수 없습니다. 해당 방을 예약하시려면 관리자에게 문의 해주세요",
						"redirect:/reserv/updateReserv?reservId="+reservId, roomNum);
			}
		} else {
			return new ErrorMessage("로그인이 되어 있지 않습니다.", "redirect:/index/main", 0);
		}

	}

	@Autowired
	HolyDayService holyDayService;
	// totalPrice를 구하는 메서드
	public int getTotalPrice(LocalDate startDate, LocalDate endDate, Room room) {
		int totalPrice = 0;
		int dv = endDate.compareTo(startDate);
		if(dv>1) dv--; // 박이 기준이기때문
		List<LocalDate> days = new ArrayList<LocalDate>();
		for (int i = 0; i <= dv; i++) { // 입실일 과 퇴실일 사이의 날짜를 list에 추가
			days.add(startDate.plusDays(i));
		}
		// 금토일 , holyDay, peekSesion이라면 weekendPrice
		for (LocalDate localDate : days) {
			if (isWeekend(localDate.getDayOfWeek().toString()) || isHolyDay(localDate) || isPeekSeason(localDate))
				totalPrice += room.getWeekendPrice();
			else
				totalPrice += room.getDayPrice();
		}

		return totalPrice;
	}
	
	// 금토일인지 확인
	private boolean isWeekend(String dayofWeek) {
		if(dayofWeek.equals("FRIDAY")|| dayofWeek.equals("SATURDAY") || dayofWeek.equals("SUNDAY")) {
			return true;
		}else return false;
	}
	
	// holyDay서비스를통해 holyDay인지 체크
	private boolean isHolyDay(LocalDate localDate) {
		int y = localDate.getYear();
		int m = localDate.getMonthValue();
		boolean ish = false;
		List<String> isHoly =  holyDayService.get(y, m);
		if(isHoly.size()>0 && !isHoly.get(0).equals("error")) {
			String dateFormmat = localDate.format(DateTimeFormatter.ofPattern("YYYYMMdd"));
			for (String holy : isHoly) {
				if(holy.equals(dateFormmat)) {
					ish = true;
					break;
				}
			}
			
			if(ish) {
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
		
	}
	
	// 날짜 체크
	public boolean dateCheck(LocalDate startDate, LocalDate endDate) {
		System.out.println(startDate);
		System.out.println(endDate);
		int hour = LocalDateTime.now().getHour();
		int minute = LocalDateTime.now().getMinute();
		// 시작일과 오늘 이 0보다 크거나 같고 시작일과 오늘이 60일 이내라면
		if (startDate.compareTo(LocalDate.now()) >= 0 && startDate.compareTo(LocalDate.now()) <= 60) {
			System.out.println("오늘 날짜와 비교 후");
			if (endDate.compareTo(startDate) >= 0 && endDate.compareTo(startDate) < 8) { // 시작일과 퇴실일의 차가 0 ~ 8 이라면
				System.out.println("시작일 과 종요일 비교 후");
				if (startDate.compareTo(LocalDate.now()) == 0) {
					 System.out.println("시작일과 현제가 0일 차인지 비교 후");
					// 현제 시간이 11시 30분 이전이라면 true
					if (hour < 11 || hour == 11 && minute <= 30) {
						// 현제 시간이 11시 30분 이전인지 확인 후
						return true;
					} else {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean peopleCountCheck(Room room, int peopleCount) {
		// 인자로 받은 인원수가 그 방의 최대 인원수와 최소인원수 사이에있는지 확인
		int max = room.getMaxPeople();
		int min = room.getMinPeople();

		if (peopleCount >= min && peopleCount <= max) {
			return true;
		} else {
			return false;
		}

	}
	// 방 번호가 101 ~ 105 &&  201~205 && 301~305 인지 확인
	public boolean roomNumCheck(int roomNum) {
		System.out.println(roomNum);
		if(roomNum>=101 && roomNum<=105 || roomNum>=201 && roomNum<=205 || roomNum>=301 && roomNum<=305) {
			return true;
		}
		else return false;
	}
	
	
	@Transactional
	public void deleteReserv(int reservId) {
		Reserv reserv = reservMapper.selectReservById(reservId);
		if(reserv.getState().equals("입금")) { // 예약이 입금 된 상태라면 
			Customer customer = customerService.getCustomerById(reserv.getUserId());
			// remove추가
			Remove remove = new Remove(0, reserv.getUserId(), reserv.getRoomId(), reserv.getStartDate(), reserv.getEndDate(), reserv.getTotalPrice()/10,customer.getName(), customer.getPhone(), null);
			removeMapper.insertRemove(remove);
		}
		
		reservMapper.deleteReserv(reservId);
	}
	
	public ReservView getReservView(int pageNum , String userId) {
		ReservView reservView = null;
		
		int firstRow = 0;
		List<Reserv> reservList = null;
		int reservCnt = reservMapper.reservCountWithUserId(userId);
		if(reservCnt>0) {
			firstRow = (pageNum-1) * RESERV_COUNT_PER_PAGE;
			reservList = reservMapper.selectReservListByUserIdWithLimit(userId, firstRow, RESERV_COUNT_PER_PAGE);
		}else {
			pageNum = 0;
		}
		reservView = new ReservView(reservCnt, pageNum, firstRow, RESERV_COUNT_PER_PAGE, reservList);
		return reservView;
	}
	
	
	private boolean isPeekSeason(LocalDate localDate) {
		if((localDate.getMonthValue()==7 && localDate.getDayOfMonth()>=17 && localDate.getDayOfMonth()<=31) || 
			(localDate.getMonthValue()==8 && localDate.getDayOfMonth()>=1 && localDate.getDayOfMonth()<=17)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Autowired
	CustomerMapper customerMapper;
	
	public ReservInfoView getAdminReservInfoView(int pageNum) {
		
		ReservInfoView reservInfoView = null;
		int firstRow = 0;
		List<ReservInfo> reservInfoList = new ArrayList<ReservInfo>();
		int reservCnt = reservMapper.reservCount();
		
		if(reservCnt>0) {
			List<Reserv> reservList = reservMapper.selectReservWithLimit(firstRow, RESERV_COUNT_PER_PAGE);
			for (Reserv reserv : reservList) {
			Customer customer = customerMapper.selectCustomerWithId(reserv.getUserId());
			Room room = roomService.getRoomById(reserv.getRoomId());
			reservInfoList.add(new ReservInfo(reserv.getId(),reserv.getUserId(), room.getConcept(),
					customer.getName(), reserv.getStartDate(),reserv.getEndDate(), reserv.getTotalPrice(), reserv.getPeopleCount(),reserv.getState(),customer.getPhone()));
			}
		}
		reservInfoView = new ReservInfoView(reservCnt, pageNum, firstRow, RESERV_COUNT_PER_PAGE, reservInfoList);
		return reservInfoView;
	}
	public List<Room> getReservCheckNoRoomId(LocalDate startDate, LocalDate endDate) {
		// 시작일과 끝일을 포함한 예약일을 가져와서 그거의 roomId로 총 room에서 삭제
		List<Reserv> reservList =  reservMapper.selectReservListByStartAndEndDate(startDate,endDate);
		List<Room> roomList =  roomService.getRoomAll();
		List<Integer> deleteIndex = new ArrayList<Integer>();
		
		for (int i = 0; i < roomList.size(); i++) {
			for (Reserv reserv : reservList) {
				if(roomList.get(i).getId() == reserv.getRoomId()) {
					deleteIndex.add(reserv.getRoomId());
				}
			}
		}
		
		int i = 0; 
		for (Integer integer : deleteIndex) {
			System.out.println(integer);
			System.out.println(roomList.get(integer-1-i));
			if(i>0)
			roomList.remove(roomList.get(integer-1-i));
			else {
				roomList.remove(roomList.get(integer-1));
			}
			i++;
		}
		return roomList;
		
	}
	// 예약의 상태를 입금과 미입금으로 변경
	public void updateReservState(int id) {
		Reserv reserv = reservMapper.selectReservById(id);
		String state = "입금";
		if(reserv.getState().equals("입금"))
			state = "미입금";
		reservMapper.updateReservState(id,state);
	}

	public ReservWithRoomNumView getReservWithRoomNumView(int pageNum , String userId) {
		ReservWithRoomNumView reservWithRoomNumView = null;
		
		int firstRow = 0;
		List<ReservWithRoomNum> reservWithRoomNumList = new ArrayList<ReservWithRoomNum>();
		List<Reserv> reservList = null;
		System.out.println(" sdsadsad : " +reservMapper.reservCountWithUserId(userId));
		int reservCnt = reservMapper.reservCountWithUserId(userId);
		if(reservCnt>0) {
			firstRow = (pageNum-1)*RESERV_COUNT_PER_PAGE;
			reservList = reservMapper.selectReservByUserId(userId);
			for (Reserv reserv : reservList) {
				Room room = roomService.getRoomById(reserv.getRoomId());
				reservWithRoomNumList.add(new ReservWithRoomNum(reserv.getId(), userId, reserv.getRoomId(),
						reserv.getStartDate(), reserv.getEndDate(), reserv.getTotalPrice(), 
						reserv.getPeopleCount(), reserv.getRegDate(), reserv.getState(),room.getRoomNum()));
			}
		}else {
			pageNum = 0;
		}
		
		reservWithRoomNumView = new ReservWithRoomNumView(reservCnt, pageNum, firstRow, RESERV_COUNT_PER_PAGE, reservWithRoomNumList);
		return reservWithRoomNumView;
	}
	
	
}
