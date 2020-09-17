package model.view;

import java.util.List;

import model.dto.Board;
import model.dto.Event;
import model.dto.EventWithThumb;

public class EventView {

	// 메시지의 총 수
	private int eventCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	// 페이지당 보여줄 정보의 수
	private int eventCountPerPage;
	// 페이지의 보여줄 정보의 리스트
	private List<EventWithThumb> eventWithThumbList;
	
	public EventView() {}

	public EventView(int eventCnt, int currentPageNum, int firstRow, int eventCountPerPage, List<EventWithThumb> eventWithThumbList) {
		this.eventCnt = eventCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.eventCountPerPage = eventCountPerPage;
		this.eventWithThumbList = eventWithThumbList;
		
		if(eventCnt>0) {
			pageTotalCount = eventCnt/eventCountPerPage;
			if(eventCnt%eventCountPerPage>0) {
				pageTotalCount++;
			}
			
		}else {
			pageTotalCount = 0;
		}
		
	}

	public int getEventCnt() {
		return eventCnt;
	}

	public void setEventCnt(int eventCnt) {
		this.eventCnt = eventCnt;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getEventCountPerPage() {
		return eventCountPerPage;
	}

	public void setEventCountPerPage(int eventCountPerPage) {
		this.eventCountPerPage = eventCountPerPage;
	}

	public List<EventWithThumb> getEventWithThumbList() {
		return eventWithThumbList;
	}

	public void setEventWithThumbList(List<EventWithThumb> eventWithThumbList) {
		this.eventWithThumbList = eventWithThumbList;
	}

	@Override
	public String toString() {
		return "EventView [eventCnt=" + eventCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
				+ pageTotalCount + ", firstRow=" + firstRow + ", eventCountPerPage=" + eventCountPerPage
				+ ", eventWithThumbList=" + eventWithThumbList + "]";
	}

	

	
	
	
}
