package model.view;

import java.util.List;

import model.dto.ReservInfo;

public class ReservInfoView {

	// 메시지의 총 수
	private int reservCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	// 페이지당 보여줄 정보의 수
	private int reservInfoCountperPage;
	// 페이지의 보여줄 정보의 리스트
	private List<ReservInfo> reservInfoList;

	public ReservInfoView() {}

	public ReservInfoView(int reservCnt, int currentPageNum, int firstRow, int reservInfoCountperPage,
			List<ReservInfo> reservInfoList) {
		this.reservCnt = reservCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.reservInfoCountperPage = reservInfoCountperPage;
		this.reservInfoList = reservInfoList;
		
		if(reservCnt>0) {
			pageTotalCount = reservCnt/reservInfoCountperPage;
			if(reservCnt%reservInfoCountperPage>0) {
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

	public int getReservInfoCountperPage() {
		return reservInfoCountperPage;
	}

	public void setReservInfoCountperPage(int reservInfoCountperPage) {
		this.reservInfoCountperPage = reservInfoCountperPage;
	}

	public List<ReservInfo> getReservInfoList() {
		return reservInfoList;
	}

	public void setReservInfoList(List<ReservInfo> reservInfoList) {
		this.reservInfoList = reservInfoList;
	}

	
	
}
