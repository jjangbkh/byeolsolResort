package model.dto;

import java.time.LocalDateTime;

public class Question {

	private int id;
	
	private String title;
	
	private String message;
	
	private String division;
	
	private String writer;
	
	private LocalDateTime regDate;
	
	private String state;
	
	public Question() {}

	
	public Question(int id, String title, String message, String division, String writer, LocalDateTime regDate,
			String state) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.division = division;
		this.writer = writer;
		this.regDate = regDate;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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


	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", message=" + message + ", division=" + division
				+ ", writer=" + writer + ", regDate=" + regDate + ", state=" + state + "]";
	}

	
	
	
}
