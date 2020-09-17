package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dto.Room;
import model.service.RoomService;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@GetMapping("/list")
	public String roomListByConcept(@RequestParam(defaultValue = "kids")String concept, Model m) {
		m.addAttribute("roomList",roomService.getRoomListByConcept(concept));
		return "roomInfo";
	}
	
	@PostMapping("/getPeopleCount")
	@ResponseBody
	public int sendMaxPeopleCount(@RequestParam(defaultValue = "0")int roomNum) {
		if(roomNum !=0) {
		Room room =roomService.getRoomByRoomNum(roomNum);
		return room.getMaxPeople();
		}else {
			return 0;
		}
	}
	
	
	
	
	
	
}
