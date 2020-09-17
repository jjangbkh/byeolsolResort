package model.dto;

public class ErrorMessage {

	private String errorMessage;

	private String result;

	private int roomNum;

	public ErrorMessage() {}

	public ErrorMessage(String errorMessage, String result, int roomNum) {
		super();
		this.errorMessage = errorMessage;
		this.result = result;
		this.roomNum = roomNum;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "ErrorMessage [errorMessage=" + errorMessage + ", result=" + result + ", roomNum=" + roomNum + "]";
	}

	
	
}
