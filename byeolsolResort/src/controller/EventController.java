package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.dto.Event;
import model.mapper.EventMapper;
import model.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {
	
	// add,update 페이지에서 startDate , endDate가 빈값으로 들어왔을 때 주는 default 값들
	private static final String DEFAULT_START_DATE = "1850-01-01";
	private static final String DEFAULT_END_DATE = "1850-12-31";

	@Autowired
	EventService eventService;

	@GetMapping("/list")
	public String goEventListView(Model m, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(required = false) String errorMessage) {
		m.addAttribute("eventView", eventService.getEventView(pageNum));
		if (errorMessage != null)
			m.addAttribute("errorMessage", errorMessage);
		return "/newsList/event";
		// eventView를 페이지에 넘겨줌
	}

	@GetMapping("/addEvent")
	public String addEventForm(HttpSession session, Model m, @RequestParam(defaultValue = "")String errorMessage) {

		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {	// 로그인 되어 있는게 관리자 라면
				if(!errorMessage.equals(""))
					m.addAttribute("errorMessage",errorMessage);
				return "/newsList/addEventForm";
			} else {
				m.addAttribute("errorMessage", "권한이 없습니다");
				return "redirect:/event/list";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/event/list";
		}
	}

	@PostMapping("/addEvent")
	public String addEvent(@RequestParam(required = false) MultipartFile uploadFile, Event event, Model m,
			HttpSession session, @RequestParam(required = false) MultipartFile thumbnail,
			@RequestParam(defaultValue = DEFAULT_START_DATE) Date start,
			@RequestParam(defaultValue = DEFAULT_END_DATE) Date end) { 	// 파일 두개가 들어 와도 되고 안들어 와도 됨
		if (session.getAttribute("userId") != null) { // 로그인이 되어 있고 관리자 이면
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if (!uploadFile.isEmpty() && !thumbnail.isEmpty()) { // 파일 두개가 empty가 아니라면(비어 있지 않다면)
					if (eventService.nullCheck(event)) {	// event에서 안들어 온 값이 있는지 확인후
						if (event.getState().equals("미상시") && !start.toString().equals(DEFAULT_START_DATE)
								&& !end.toString().equals(DEFAULT_END_DATE)
								|| event.getState().equals("상시") && start.toString().equals(DEFAULT_START_DATE)
										&& end.toString().equals(DEFAULT_END_DATE)) { 	
							// state가 미상시 이며 start , end 가 default값이 아닐 때 이거나 state가 상시 이면서 start,end 가 default 값일 때
							if (event.getState().equals("상시")	// state가 상시 이거나 state가 미상시 이면서 날짜 맞는 날짜인지 확인 이 되었을 때
									|| event.getState().equals("미상시") && eventService.stateNoDateCheck(start, end)) {
									event.setStartDate(start.toLocalDate()); // event의 startDate와 endDate에 받은 start,end를 localDate로 변경 시켜서 setting
									event.setEndDate(end.toLocalDate());
									// event db, event_img , ftp에 각각 추가 이 과정에서 문제가 하나라도 있다면 IOExeption 발생
									if (eventService.addEvent(event, uploadFile, thumbnail)) {
										return "redirect:/event/list";
									} else {
										m.addAttribute("Event", event);
										return "redirect:/event/addEvent";
									}
							} else {
								m.addAttribute("errorMessage", "날짜 입력을 확인 하여 주세요");
								return "redirect:/event/addEvent";
							}
						} else {
							m.addAttribute("errorMessage", "날짜 입력을 확인 하여 주세요");
							return "redirect:/event/addEvent";
						}
					} else {
						m.addAttribute("errorMessage", "공백 값이 있습니다.");
						return "redirect:/event/addEvent";
					}
				} else {
					m.addAttribute("errorMessage", "파일업로드는 무조건 해야 합니다.");
					return "redirect:/event/addEvent";
					
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없습니다");
				return "redirect:/event/list";
			}
		} else {
			m.addAttribute("errorMessage", "권한이 없습니다");
			return "redirect:/event/list";
		}
	}

	@GetMapping("/updateEvent")
	public String updateEvent(@RequestParam(defaultValue = "0")int id, Model m, HttpSession session, @RequestParam(defaultValue = "")String errorMessage) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) { // 이벤트를 업데이트 하려 하는 데 관리자만 사용
				if (id != 0) {
					Event event = eventService.getEvent(id);
					if (event != null && id>0) {
						m.addAttribute("event", event);
						if(!errorMessage.equals(""))
							m.addAttribute("errorMessage",errorMessage);
						return "/newsList/updateEventForm";
					} else {
						m.addAttribute("errorMessage", "잘못된 접근");
						return "redirect:/event/list";
					}
				} else {
					m.addAttribute("errorMessage", "잘못된 접근");
					return "redirect:/event/list";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근");
				return "redirect:/event/list";
			}
		} else {
			m.addAttribute("errorMessage", "권한이 없는 접근");
			return "redirect:/event/list";
		}

	}

	@PostMapping("/updateEvent")
	public String updateEvent(@RequestParam(required = false) MultipartFile uploadFile, Event event,
			@RequestParam(required = false) MultipartFile thumbnail, HttpSession session, Model m,
			@RequestParam(defaultValue = DEFAULT_START_DATE) Date start,
			@RequestParam(defaultValue = DEFAULT_END_DATE) Date end) {
		boolean isThumbUp = true;	// 썸네일 update시 제대로 되어있는지 확인
		boolean isDateCheck = false;	// 날짜가 제대로 된 값인지 확인	
		boolean isNullCheck = false;	// 비어있는 값이 있는지 확인
		isNullCheck = eventService.nullCheckUpdate(event);	
		Event eventD = eventService.getEvent(event.getId());
		if (isNullCheck) {	// 비어있는 값이 없다면
			if (event.getState().equals("미상시") && !start.toString().equals(DEFAULT_START_DATE)
					&& !end.toString().equals(DEFAULT_END_DATE)
					|| event.getState().equals("상시") && start.toString().equals(DEFAULT_START_DATE)
							&& end.toString().equals(DEFAULT_END_DATE)
					|| event.getState().equals("상시") && start.toLocalDate().equals(eventD.getStartDate())
							&& end.toLocalDate().equals(eventD.getEndDate())) { // 위와 같지만 state가 상시이면서 입력한 값이 전 바꾸기 전 날짜와 같다면 
				if (event.getState().equals("미상시") && eventService.stateNoDateCheck(start, end)
						|| event.getState().equals("상시")) {
					event.setStartDate(start.toLocalDate());
					event.setEndDate(end.toLocalDate());
					isDateCheck = true;
				}
			}
		}
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) {
				if (isNullCheck) {
					if (isDateCheck) {
						Event ev = eventService.getEvent(event.getId());
						if (uploadFile.isEmpty()) {
							event.setImgPath(ev.getImgPath());
							if (!thumbnail.isEmpty()) {
								if (isThumbUp = eventService.deleteEventImgThumbnaul(event.getId()))
									;
								isThumbUp = eventService.addEventImgThumbnail(thumbnail, event.getId());
								System.out.println(isThumbUp);
							}
							if (isThumbUp) {
								eventService.updateEvent(event);
								return "redirect:/event/list";
							} else {
								m.addAttribute("errorMessage", "업로드할 썸네일 이미지를 확인하여 주세요");
								return "redirect:/event/updateEvent";
							}
						} else {
							event.setImgPath(ev.getImgPath());
							if (eventService.updateEventWithFile(uploadFile, event)) {
								if (!thumbnail.isEmpty()) {
									if (isThumbUp = eventService.deleteEventImgThumbnaul(event.getId()))
										;
									isThumbUp = eventService.addEventImgThumbnail(thumbnail, event.getId());
								}
								if (isThumbUp) {
									return "redirect:/event/list";
								} else {
									m.addAttribute("errorMessage", "잘못된 접근");
									return "redirect:/event/list";
								}
							} else {
								return "redirect:/event/updateEvent?id=" + event.getId();
							}
						}
					} else {
						m.addAttribute("errorMessage", "날짜를 다시 확인 하여 주세요");
						return "redirect:/event/updateEvent?id=" + event.getId();
					}
				} else {
					m.addAttribute("errorMessage", "입력하지 않은 값이 있습니다.");
					return "redirect:/event/updateEvent?id=" + event.getId();
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근 입니다,");
				return "redirect:/index/main";
			}
		} else {
			m.addAttribute("errorMessage", "로그인이 되어 있지 않습니다.");
			return "redirect:/index/main";
		}

	}

	@GetMapping("/deleteEvent")
	public String deleteEvent(@RequestParam(defaultValue = "0") int id, Model m, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			if (userId.equals("admin")) { // 삭제 할 때 로그인 되어있는게 관리자 라면
				if (id != 0) {
					if (eventService.getEvent(id) != null) {
						try {
							if (eventService.removeEvent(id)) { // id에 해당하는 event삭제 
								return "redirect:/event/list";
							} else
								return "redirect:/event/detailEvent?id=" + id;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "redirect:/event/list";
						}
					} else {
						m.addAttribute("errorMessage", "잘못된 접근");
						return "redirect:/event/list";
					}
				} else {

					m.addAttribute("errorMessage", "잘못된 접근");
					return "redirect:/event/list";
				}
			} else {
				m.addAttribute("errorMessage", "권한이 없는 접근");
				return "redirect:/event/list";
			}
		} else {
			m.addAttribute("errorMessage", "권한이 없는 접근");
			return "redirect:/event/list";
		}
	}

	@GetMapping("/detailEvent")
	public String detailEvent(@RequestParam(defaultValue = "0") int id, Model m) {
		if (id > 0) {
			Event event = eventService.getEvent(id);
			if (event != null) {
				m.addAttribute("event", event);
				return "/newsList/detailEvent";
			} else {
				m.addAttribute("errorMessage", "잘못된  접근");
				return "redirect:/event/list";
			}
		} else {
			m.addAttribute("errorMessage", "잘못된 접근");
			return "redirect:/event/list";
		}

	}

}
