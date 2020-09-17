package model.dto;

import java.time.LocalDateTime;

public class Comment {
	
	private int id;
	private int boardId;
	private String message;
	private String userId;
	private LocalDateTime wDate;
	
	
	public Comment() {	}

	public Comment(int id, int boardId, String message, String userId, LocalDateTime wDate) {
		super();
		this.id = id;
		this.boardId = boardId;
		this.message = message;
		this.userId = userId;
		this.wDate = wDate;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBoardId() {
		return boardId;
	}


	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public LocalDateTime getwDate() {
		return wDate;
	}


	public void setwDate(LocalDateTime wDate) {
		this.wDate = wDate;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", boardId=" + boardId + ", message=" + message + ", userId=" + userId + ", wDate="
				+ wDate + "]";
	}

	
	
	
	
}
