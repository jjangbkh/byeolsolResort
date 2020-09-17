package model.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Reserv;

public interface ReservMapper {

	public void insertReserv (Reserv reserv);
	
	public void updateReserv (Reserv reserv);
	
	public void deleteReserv (int id);
	
	public List<Reserv> selectReservList();
	
	public List<Reserv> selectReservListByUserIdWithLimit(@Param("userId")String userId, @Param("firstRow")int firstRow, @Param("reservCountPerPage")int reservCountPerPage);
	
	public List<Reserv> selectReservByRoomIdWithDate(@Param("roomId")int roomId,@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

	public Reserv selectReservById(int id);

	public void deleteReservByState();
	
	public int reservCount();

	public List<Reserv> selectReservByUserId(String userId);

	public List<Reserv> selectReservWithLimit(@Param("firstRow")int firstRow, @Param("reservCountPerPage")int reservCountPerPage);

	public List<Reserv> selectReservListByStartAndEndDate(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);

	public void updateReservState(@Param("id") int id , @Param("state")String state);
	
	public int reservCountWithUserId(String userId);
	
	
	
}
