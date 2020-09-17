package model.dto;

import java.time.LocalDate;

public class ReservInfo {
	
	private int id;
	
	private String userId;
	
	private String concept;
	
	private String userName;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private int totalPrice;
	
	private int peopleCount;
	
	private String state;
	
	private String phone;
	
	public ReservInfo() {}

	public ReservInfo(int id , String userId, String concept, String userName, LocalDate startDate, LocalDate endDate,
			int totalPrice, int peopleCount, String state, String phone) {
		this.id = id;
		this.userId = userId;
		this.concept = concept;
		this.userName = userName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.peopleCount = peopleCount;
		this.state = state;
		this.phone = phone;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ReservInfo [id=" + id + ", userId=" + userId + ", concept=" + concept + ", userName=" + userName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", totalPrice=" + totalPrice + ", peopleCount="
				+ peopleCount + ", state=" + state + ", phone=" + phone + "]";
	}
	
	

}
