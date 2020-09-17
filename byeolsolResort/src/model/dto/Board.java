package model.dto;

import java.time.LocalDateTime;

public class Board {

	private int id;
	private String title;
	private String content;
	private String userId;
	private String state;
	private LocalDateTime wDate;
	private String firstPath;
	private String secondPath;
	private String thirdPath;
	private int commnetCount;
	
	public Board() {}

	public Board(int id, String title, String content, String userId, String state, LocalDateTime wDate,
			String firstPath, String secondPath, String thirdPath, int commnetCount) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.state = state;
		this.wDate = wDate;
		this.firstPath = firstPath;
		this.secondPath = secondPath;
		this.thirdPath = thirdPath;
		this.commnetCount = commnetCount;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDateTime getwDate() {
		return wDate;
	}

	public void setwDate(LocalDateTime wDate) {
		this.wDate = wDate;
	}

	public String getFirstPath() {
		return firstPath;
	}

	public void setFirstPath(String firstPath) {
		this.firstPath = firstPath;
	}

	public String getSecondPath() {
		return secondPath;
	}

	public void setSecondPath(String secondPath) {
		this.secondPath = secondPath;
	}

	public String getThirdPath() {
		return thirdPath;
	}

	public void setThirdPath(String thirdPath) {
		this.thirdPath = thirdPath;
	}

	public int getCommnetCount() {
		return commnetCount;
	}

	public void setCommnetCount(int commnetCount) {
		this.commnetCount = commnetCount;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", state="
				+ state + ", wDate=" + wDate + ", firstPath=" + firstPath + ", secondPath=" + secondPath
				+ ", thirdPath=" + thirdPath + ", commnetCount=" + commnetCount + "]";
	}
	
	
	
	
}
