package model.view;

import java.time.LocalDateTime;
import java.util.List;

import model.dto.Comment;

public class CommentView {

	// 메시지의 총 수
	private int commentCnt;
	// 현제 페이지 번호
	private int currentPageNum;
	// 총 페이지 수
	private int pageTotalCount;
	// 몇번 부터 조회할 지 정하는 수
	private int firstRow;
	
	// 페이지당 보여줄 정보의 수
	private int commentCountperPage;
	
	private List<Comment> commentList;
	
	public CommentView() {}

	public CommentView(int commentCnt, int currentPageNum, int firstRow,
			int commentCountperPage, List<Comment> commentList) {
		super();
		this.commentCnt = commentCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.commentCountperPage = commentCountperPage;
		this.commentList = commentList;
		
		if(commentCnt>0) {
			pageTotalCount = commentCnt/commentCountperPage;
			if(commentCnt%commentCountperPage>0) {
				pageTotalCount++;			}
		}else pageTotalCount = 0;
		
	}
	
	
	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
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

	public int getCommentCountperPage() {
		return commentCountperPage;
	}

	public void setCommentCountperPage(int commentCountperPage) {
		this.commentCountperPage = commentCountperPage;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "CommentView [commentCnt=" + commentCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
				+ pageTotalCount + ", firstRow=" + firstRow + ", commentCountperPage="
				+ commentCountperPage + ", commentList=" + commentList + "]";
	}
	
	

	
	

}
