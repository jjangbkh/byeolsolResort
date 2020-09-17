package model.mapper;

import model.dto.EventImg;

public interface EventImgMapper {

	public void insertEventImg(EventImg eventImg);
	
	public void updateEventImg(EventImg eventImg);
	
	public void deleteEventImg(int eventId);
	
	public EventImg selectEventImgByEventId(int eventId);
	
}
