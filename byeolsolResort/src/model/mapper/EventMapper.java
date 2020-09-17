package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Event;

public interface EventMapper {
	
	public void insertEvent(Event event);
	
	public void updateEvent(Event event);
	
	public void deleteEvent(int id);
	
	public Event selectEventById(int id);
	
	public List<Event> selectEventList();
	
	public List<Event> selectEventListWithLimit(@Param("firstRow")int firstRow, @Param("eventCountPerPage")int eventCountPerPage);

	public int countEvent();
	
}
