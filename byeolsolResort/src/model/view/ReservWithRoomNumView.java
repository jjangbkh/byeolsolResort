package model.view;

import java.util.List;

import model.dto.ReservWithRoomNum;

public class ReservWithRoomNumView {

	private int reservCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	// 페이지당 보여줄 정보의 수
	private int reservWithRoomNumCountPerPage;
	// 페이지의 보여줄 정보의 리스트
	private List<ReservWithRoomNum> ReservWithRoomNumList;
	
	public ReservWithRoomNumView() {}

	public ReservWithRoomNumView(int reservCnt, int currentPageNum, int firstRow, int reservWithRoomNumCountPerPage,
			List<ReservWithRoomNum> reservWithRoomNumList) {
		super();
		this.reservCnt = reservCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.reservWithRoomNumCountPerPage = reservWithRoomNumCountPerPage;
		ReservWithRoomNumList = reservWithRoomNumList;
		
		if (reservCnt>0) {
			pageTotalCount = reservCnt/reservWithRoomNumCountPerPage;
			if(reservCnt%reservWithRoomNumCountPerPage>0) {
				pageTotalCount++;
			}
		}else {
			pageTotalCount = 0;
		}
		
	}

	public int getReservCnt() {
		return reservCnt;
	}

	public void setReservCnt(int reservCnt) {
		this.reservCnt = reservCnt;
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

	public int getReservWithRoomNumCountPerPage() {
		return reservWithRoomNumCountPerPage;
	}

	public void setReservWithRoomNumCountPerPage(int reservWithRoomNumCountPerPage) {
		this.reservWithRoomNumCountPerPage = reservWithRoomNumCountPerPage;
	}

	public List<ReservWithRoomNum> getReservWithRoomNumList() {
		return ReservWithRoomNumList;
	}

	public void setReservWithRoomNumList(List<ReservWithRoomNum> reservWithRoomNumList) {
		ReservWithRoomNumList = reservWithRoomNumList;
	}

	@Override
	public String toString() {
		return "ReservWithRoomNumView [reservCnt=" + reservCnt + ", currentPageNum=" + currentPageNum
				+ ", pageTotalCount=" + pageTotalCount + ", firstRow=" + firstRow + ", reservWithRoomNumCountPerPage="
				+ reservWithRoomNumCountPerPage + ", ReservWithRoomNumList=" + ReservWithRoomNumList + "]";
	}
	
	
}
