package model.service;

import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexService {

	public String returnPath (String classification , String value) {
		
		String path = "";
		
		switch(classification) {
		case "mainImg" : case "byeolsolinfo": case "roomMain": case "healingRoom": case "gameRoom": case "kidsRoom": 
		case "trip": case "moun": case "golf": case "logo": case "question": case "reserv": 
			path += "/index"; break;
		case "board" : path+="/board"; break;
		case "event" : path +="/event"; break;
		case "myPage": path += "/cus"; break;
		}
		
		switch(value) {
		case "main": case "title1" : case "title2":  path+="/main"; break;
		case "R1subImg": path += "/roomInfo_01"; break;
		case "R2subImg": path += "/roomInfo_02"; break;
		case "R3subImg": path += "/roomInfo_03"; break;
		case "RMsubimg": path += "/roomMain"; break;
		case "byeolsolnewssub" : path+="/list"; break;
		case "myPageSub": path+="adminUserInfo"; break;
		case "moun2": path += "/moun"; break;
		case "tripsub" : path +="/trip"; break;
		case "golfsub" : path += "/golf"; break;
		case "reservSub" : path += "/guestroom"; break;
		case "questionSub": path += "/adminQnA";break;
		case "infosub" : path += "/byeolsolInfo";break;
		}
		
		return path;
	}
	
	
}
