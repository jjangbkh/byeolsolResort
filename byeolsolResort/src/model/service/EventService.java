package model.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import model.dto.Event;
import model.dto.EventImg;
import model.dto.EventWithThumb;
import model.mapper.EventImgMapper;
import model.mapper.EventMapper;
import model.view.EventView;

@Service("eventService")
public class EventService {
	private static final int EVENT_COUNT_PER_PAGE = 5;

	@Autowired
	EventMapper eventMapper;

	@Autowired
	EventImgMapper eventImgMapper;

	public EventView getEventView(int pageNum) {
		EventView eventview = null;
		int firstRow = 0;
		List<Event> eventList = null;
		List<EventWithThumb> eventWithThumbList = new ArrayList<EventWithThumb>();
		int eventCnt = eventMapper.countEvent();

		if (eventCnt > 0) {
			firstRow = (pageNum - 1) * EVENT_COUNT_PER_PAGE;
			eventList = eventMapper.selectEventListWithLimit(firstRow, EVENT_COUNT_PER_PAGE);
			
			for (Event event : eventList) {
				EventImg eventImg = eventImgMapper.selectEventImgByEventId(event.getId());
				eventWithThumbList.add(new EventWithThumb(event, eventImg.getImgPath()));
			}
			
			
			
		} else {
			pageNum = 0;
		}

		eventview = new EventView(eventCnt, pageNum, firstRow, EVENT_COUNT_PER_PAGE, eventWithThumbList);
		return eventview;
	}

	@Autowired
	FtpService ftpService;
	
	// IOExeption이 발생하면 rollback함
	public boolean addEvent(Event event, MultipartFile uploadFile, MultipartFile thumbnail) {
		if (typeCheck(uploadFile)&& typeCheck(thumbnail)) { // 파일의 type을 
			// 추가할때 날짜와 시간을 갖고 폴더 생성
			String addTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss_SSSS"));
			// ftp에 이미지 업로드
			ftpService.ftpEventImg(uploadFile, addTime);
			// event db에 imgPath에 저장
			event.setImgPath("https://gyonewproject.000webhostapp.com/byeolsolResort/event/" + addTime + "/"
					+ uploadFile.getOriginalFilename());
				eventMapper.insertEvent(event);
				// 업로드 실패시 IOExeption발생
				if(!addEventImgThumbnail(thumbnail,event.getId())) {
					String Time = event.getImgPath().substring(event.getImgPath().indexOf('/', event.getImgPath().indexOf("event")) + 1,
							event.getImgPath().lastIndexOf('/')); //시간가져오고
					ftpService.ftpdeleteEvent(event.getImgPath(), Time);
					eventMapper.deleteEvent(event.getId());
				}
				return true;
			
		} else {
			return false;
		}
	}

	public boolean typeCheck(MultipartFile uploadFile) {
		// 받아온 파일의 original이름에 .부터 마지막까지를 저장
		String fileType = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf('.'),
				uploadFile.getOriginalFilename().length());
		// fileType을 소문자로 변경 
		fileType = fileType.toLowerCase();
		// 이미지 파일 형식인지 확인
		if (fileType.equals(".jpg") || fileType.equals(".png") || fileType.equals(".jpeg")) {
			return true;
		} else
			return false;
	}
	
	// id를갖고 db에서가져옴
	public Event getEvent(int id) {
		return eventMapper.selectEventById(id);
	}

	// 파일이 있는 업데이트
	public boolean updateEventWithFile(MultipartFile uploadFile, Event event) {
		if (typeCheck(uploadFile)) {// 이미지 타입 이면
			String Time = event.getImgPath().substring(event.getImgPath().indexOf('/', event.getImgPath().indexOf("event")) + 1,
					event.getImgPath().lastIndexOf('/')); //시간가져오고
			ftpService.ftpdeleteEvent(event.getImgPath(), Time); // 이미 있는 이미지 삭제
			ftpService.ftpEventImg(uploadFile, Time);	// 이미지 올리기
			event.setImgPath("https://gyonewproject.000webhostapp.com/byeolsolResort/event/" + Time + "/"
					+ uploadFile.getOriginalFilename()); // event의 이미지 setting 
			eventMapper.updateEvent(event);
			return true;
		} else
			return false;

	}

	public void updateEvent(Event event) {
		eventMapper.updateEvent(event);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {IOException.class})
	public boolean removeEvent(int id) throws IOException{
		
		Event event = eventMapper.selectEventById(id);
		if(event!=null) {	// null이 아닐때
		String Time = event.getImgPath().substring(event.getImgPath().indexOf('/', event.getImgPath().indexOf("event")) + 1,
				event.getImgPath().lastIndexOf('/')); //시간가져오고
		if(!ftpService.ftpdeleteEvent(event.getImgPath(), Time)) { // ftp 에 있는 이미지 삭제
			throw new IOException();
		}
		if (deleteEventImgThumbnaul(id)) {	// ftp 에 있는 이미지 삭제
			eventMapper.deleteEvent(id);
			return true;
		} else {
			throw new IOException();
		}
		}else {
			return false;
		}
		
	}

	public boolean addEventImgThumbnail(MultipartFile thumbnail, int eventId) {
		if (ftpService.fileTypeCheck(thumbnail)) { // 썸네일 이미지 타입 체크
			if (ftpService.ftpEventThumbImg(thumbnail, eventId)) { // 썸네일 추가
				eventImgMapper.insertEventImg(	// 
						new EventImg(0, eventId, "https://gyonewproject.000webhostapp.com/byeolsolResort/event/event_" + eventId
								+ "_thumbnail/" + thumbnail.getOriginalFilename())); // eventImg추가
				return true; 
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	public boolean deleteEventImgThumbnaul(int eventId) {
		EventImg eventImg = eventImgMapper.selectEventImgByEventId(eventId);//eventId로 eventImg가져오기
		if(ftpService.ftpDeleteEventImg(eventImg.getImgPath(), eventId)) {	// ftp이미지 삭제
			eventImgMapper.deleteEventImg(eventId);	// eventImg 삭제
			return true;
		}else
		return false; 

	}
	
	public String getImgPath (int eventId) {
		// imgPath얻기
		return eventImgMapper.selectEventImgByEventId(eventId).getImgPath();
	}

	public boolean stateNoDateCheck(Date start, Date end) {
		LocalDate startDate = start.toLocalDate();
		LocalDate endDate = end.toLocalDate();
		LocalDate now = LocalDate.now();
		if(startDate.compareTo(now) >=0 && endDate.compareTo(startDate)>=0) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public boolean nullCheck(Event event) {
		if (event.getState() != null && event.getTitle() != null) {
			System.out.println(event.getState() + " , " + event.getId() + " , " + event.getTitle());
			return true;
		}else return false;
	}
	
	public boolean nullCheckUpdate(Event event) {
		if (event.getState() != null && event.getTitle() != null && event.getId()>0) {
			System.out.println(event.getState() + " , " + event.getId() + " , " + event.getTitle());
			return true;
		}else return false;
		
	}
	
	

}
