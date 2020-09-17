package model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
	
	private int id;
	
	private String title;
	
	private String imgPath;
	
	private LocalDateTime regDate;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String state;
	
	
	public Event() {}

	public Event(int id, String title, String imgPath, LocalDateTime regDate, LocalDate startDate, LocalDate endDate,
			String state) {
		super();
		this.id = id;
		this.title = title;
		this.imgPath = imgPath;
		this.regDate = regDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", imgPath=" + imgPath + ", regDate=" + regDate + ", startDate="
				+ startDate + ", endDate=" + endDate + ", state=" + state + "]";
	}
	
	
	
}
