package model.view;

import java.util.List;

import model.dto.Answer;

public class AnswerView {

	// 정보의 총 수
	private int answerCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	// 페이지당 보여줄 정보의 수
	private int answerCountPerPage;
	// 페이지의 보여줄 정보의 리스트
	private List<Answer> answerList;

	public AnswerView() {}

	public AnswerView(int answerCnt, int currentPageNum, int firstRow, int answerCountPerPage,
			List<Answer> answerList) {
		this.answerCnt = answerCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.answerCountPerPage = answerCountPerPage;
		this.answerList = answerList;
		
		// 정보의 수가 0보다 크다면
		if(answerCnt>0) {
			// 페이지 총 수  = 정보의 수 / 페이지당 보여줄 정보의 수
			pageTotalCount = answerCnt/answerCountPerPage;
			// 정보의 수 % 페이지당 보여줄 정보의 수 가 0보다 크면 
			if(answerCnt%answerCountPerPage>0) {
				// 총 페이지 수 +1
				pageTotalCount++;
			}
		}else {
			pageTotalCount = 0;
		}
		
	}

	public int getAnswerCnt() {
		return answerCnt;
	}

	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
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

	public int getAnswerCountPerPage() {
		return answerCountPerPage;
	}

	public void setAnswerCountPerPage(int answerCountPerPage) {
		this.answerCountPerPage = answerCountPerPage;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	@Override
	public String toString() {
		return "AnswerView [answerCnt=" + answerCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
				+ pageTotalCount + ", firstRow=" + firstRow + ", answerCountPerPage=" + answerCountPerPage
				+ ", answerList=" + answerList + "]";
	}
	
}
