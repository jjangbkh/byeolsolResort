package model.dto;

public class EventWithThumb {

	private Event event;
	
	private String imgPath;
	
	public EventWithThumb() {}

	public EventWithThumb(Event event, String imgPath) {
		super();
		this.event = event;
		this.imgPath = imgPath;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "EventWithThumb [event=" + event + ", imgPath=" + imgPath + "]";
	}
	
	
	
	
}
