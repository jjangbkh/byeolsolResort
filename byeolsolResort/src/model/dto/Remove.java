package model.dto;

import java.time.LocalDate;

public class Remove {

	private int id;
	private String userId;
	private int roomId;
	private LocalDate startDate;
	private LocalDate endDate;
	private int totalPrice;
	private String userName;
	private String userPhone;
	private String state;

	public Remove() {}

	public Remove(int id, String userId, int roomId, LocalDate startDate, LocalDate endDate, int totalPrice,
			String userName, String userPhone, String state) {
		super();
		this.id = id;
		this.userId = userId;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.userName = userName;
		this.userPhone = userPhone;
		this.state = state;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

	@Override
	public String toString() {
		return "Remove [id=" + id + ", userId=" + userId + ", roomId=" + roomId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", totalPrice=" + totalPrice + ", userName=" + userName + ", userPhone="
				+ userPhone + ", state=" + state + "]";
	}

	
	
}
