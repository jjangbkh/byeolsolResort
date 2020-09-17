package model.dto;

public class EventImg {
	
	private int id;
	
	private int eventId;
	
	private String imgPath;
	
	public EventImg() {}

	public EventImg(int id, int eventId, String imgPath) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.imgPath = imgPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "EventImg [id=" + id + ", eventId=" + eventId + ", imgPath=" + imgPath + "]";
	}

	
	
}
