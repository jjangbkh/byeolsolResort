package model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dto.Event;
import model.dto.Reserv;
import model.mapper.EventMapper;
import model.mapper.ReservMapper;

@Service("scheduleService")
public class ScheduleService {

	@Autowired
	ReservMapper reservMapper;
	
	@Autowired
	EventMapper eventMapper;

	public void deleteReserv() {
		List<Reserv> reservList = reservMapper.selectReservList();// 예약 정보를 모두 가져옴
		if (reservList.size() > 0) { // 가저온 예약 정보의 수가 0보다 크면
			LocalDate now = LocalDate.now();	// 현재 날짜를 구함
			LocalDateTime nowWithTime = LocalDateTime.now();	// 현재 날짜와 시간을 구함
			for (Reserv reserv : reservList) {	// 반복문을 돌림
				if(reserv.getState().equals("미입금")) {	// 예약의 state가 미입금이면
					if(reserv.getStartDate().equals(now) && nowWithTime.getHour()>11 || reserv.getStartDate().equals(now) && nowWithTime.getHour()==11 && nowWithTime.getMinute()>=30) { // 예약 시작일이 오늘이라면 11시 30분 이 넘어갔을 때
						// 예약 정보 삭제
						reservMapper.deleteReserv(reserv.getId());
					}
					// 현제 와 예약 등록일의 차가 2일 이상이라면 해당 예약 삭제
					if(now.compareTo(reserv.getRegDate().toLocalDate())>= 2) {
						reservMapper.deleteReserv(reserv.getId());
						// 예약의 퇴실날자와 오늘이 같은날이라면 예약 삭제
					}else if(reserv.getEndDate().equals(now)) {
						reservMapper.deleteReserv(reserv.getId());
					}
				}
			}
		}
	}
	
	public void deleteEvent() {
		List<Event> eventList = eventMapper.selectEventList();
		LocalDate now = LocalDate.now().plusDays(1);
		List<Integer> deleteIndex = new ArrayList<Integer>();
		for (Event event : eventList) {
			if(event.getEndDate().equals(now)) {
				deleteIndex.add(event.getId());
			}
		}
		for (Integer index : deleteIndex) {
			eventMapper.deleteEvent(index);
		}
	}

}
