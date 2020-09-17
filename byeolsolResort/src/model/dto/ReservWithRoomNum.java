package model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservWithRoomNum {

	private int id;
	
	private String userId;
	
	private int roomId;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private int totalPrice;
	
	private int peopleCount;
	
	private LocalDateTime regDate;
	
	private String state;
	
	private int roomNum;
	
	public ReservWithRoomNum() {}

	public ReservWithRoomNum(int id, String userId, int roomId, LocalDate startDate, LocalDate endDate, int totalPrice,
			int peopleCount, LocalDateTime regDate, String state, int roomNum) {
		super();
		this.id = id;
		this.userId = userId;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.peopleCount = peopleCount;
		this.regDate = regDate;
		this.state = state;
		this.roomNum = roomNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "ReservWithRoomNum [id=" + id + ", userId=" + userId + ", roomId=" + roomId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", totalPrice=" + totalPrice + ", peopleCount=" + peopleCount + ", regDate="
				+ regDate + ", state=" + state + ", roomNum=" + roomNum + "]";
	}
	
	
	
}
