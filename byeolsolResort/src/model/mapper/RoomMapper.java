package model.mapper;

import java.util.List;

import model.dto.Room;

public interface RoomMapper {

	public Room selectRoomById(int id);
	public List<Room> selectRoomList();
	public List<Room> selectRoomListByConcept(String concept);
	public Room selectRommByRoomNum(int roomNum);
}
