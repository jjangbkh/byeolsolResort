package model.view;

import java.util.List;

import model.dto.Remove;

public class RemoveView {

	// 메시지의 총 수
	private int removeCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	// 페이지당 보여줄 정보의 수
	private int removeCountperPage;
	// 페이지의 보여줄 정보의 리스트
	private List<Remove> removeList;
	
	public RemoveView() {}

	public RemoveView(int removeCnt, int currentPageNum, int firstRow, int removeCountperPage,
			List<Remove> removeList) {
		super();
		this.removeCnt = removeCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.removeCountperPage = removeCountperPage;
		this.removeList = removeList;
		
		if(removeCnt>0) {
			pageTotalCount = removeCnt/removeCountperPage;
			if(removeCnt%removeCountperPage>0)pageTotalCount++;
		}else {
			pageTotalCount = 0;
		}
		
	}

	public int getRemoveCnt() {
		return removeCnt;
	}

	public void setRemoveCnt(int removeCnt) {
		this.removeCnt = removeCnt;
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

	public int getRemoveCountperPage() {
		return removeCountperPage;
	}

	public void setRemoveCountperPage(int removeCountperPage) {
		this.removeCountperPage = removeCountperPage;
	}

	public List<Remove> getRemoveList() {
		return removeList;
	}

	public void setRemoveList(List<Remove> removeList) {
		this.removeList = removeList;
	}

	@Override
	public String toString() {
		return "RemoveView [removeCnt=" + removeCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
				+ pageTotalCount + ", firstRow=" + firstRow + ", removeCountperPage=" + removeCountperPage
				+ ", removeList=" + removeList + "]";
	}
	
	
	
	
	

}
