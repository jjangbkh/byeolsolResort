package model.view;

import java.util.List;

import model.dto.Question;

public class QuestionView {

	// 메시지의 총 수
	private int questionCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	// 페이지당 보여줄 정보의 수
	private int questionCountPerPage;
	// 페이지의 보여줄 정보의 리스트
	private List<Question> questionList;

	public QuestionView() {}

	public QuestionView(int questionCnt, int currentPageNum, int firstRow, int questionCountPerPage,
			List<Question> questionList) {
		this.questionCnt = questionCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.questionCountPerPage = questionCountPerPage;
		this.questionList = questionList;
		
		if (questionCnt>0) {
			pageTotalCount = questionCnt/questionCountPerPage;
			if(questionCnt%questionCountPerPage>0) {
				pageTotalCount++;
			}
		}else {
			pageTotalCount = 0;
		}
		
	}

	public int getQuestionCnt() {
		return questionCnt;
	}

	public void setQuestionCnt(int questionCnt) {
		this.questionCnt = questionCnt;
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

	public int getQuestionCountPerPage() {
		return questionCountPerPage;
	}

	public void setQuestionCountPerPage(int questionCountPerPage) {
		this.questionCountPerPage = questionCountPerPage;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	@Override
	public String toString() {
		return "QuestionView [questionCnt=" + questionCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
				+ pageTotalCount + ", firstRow=" + firstRow + ", questionCountPerPage=" + questionCountPerPage
				+ ", questionList=" + questionList + "]";
	}
	
	
	
}
