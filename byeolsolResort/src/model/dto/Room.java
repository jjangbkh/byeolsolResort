package model.dto;

public class Room {
	
	private int id;
	private int roomNum;
	private int minPeople;
	private int maxPeople;
	private int dayPrice;
	private int weekendPrice;
	private String concept;
	
	
	public Room() {	}

	public Room(int id, int roomNum, int minPeople, int maxPeople, int dayPrice, int weekendPrice, String concept) {
		super();
		this.id = id;
		this.roomNum = roomNum;
		this.minPeople = minPeople;
		this.maxPeople = maxPeople;
		this.dayPrice = dayPrice;
		this.weekendPrice = weekendPrice;
		this.concept = concept;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getMinPeople() {
		return minPeople;
	}

	public void setMinPeople(int minPeople) {
		this.minPeople = minPeople;
	}

	public int getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

	public int getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(int dayPrice) {
		this.dayPrice = dayPrice;
	}

	public int getWeekendPrice() {
		return weekendPrice;
	}

	public void setWeekendPrice(int weekendPrice) {
		this.weekendPrice = weekendPrice;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNum=" + roomNum + ", minPeople=" + minPeople + ", maxPeople=" + maxPeople
				+ ", dayPrice=" + dayPrice + ", weekendPrice=" + weekendPrice + ", concept=" + concept + "]";
	}
	
	
}
