package model.view;

import java.util.List;

import model.dto.Board;

public class BoardView {
	
		// 메시지의 총 수
		private int boardCnt;
		// 현제 페이지 번호
		private int currentPageNum;
		// 총 페이지 수
		private int pageTotalCount;
		// 몇번 부터 조회할 지 정하는 수
		private int firstRow;
		// 페이지당 보여줄 정보의 수
		private int boardCountperPage;
		// 페이지의 보여줄 정보의 리스트
		private List<Board> boardList;
		
		public BoardView() {}

		public BoardView(int boardCnt, int currentPageNum, int firstRow, 
				int boardCountperPage, List<Board> boardList) {
			super();
			this.boardCnt = boardCnt;
			this.currentPageNum = currentPageNum;
			this.firstRow = firstRow;
			this.boardCountperPage = boardCountperPage;
			this.boardList = boardList;
			
			if(boardCnt<=0) {
				pageTotalCount = 0;
			}else {
				pageTotalCount = boardCnt/boardCountperPage;
				if(boardCnt%boardCountperPage>0) {
					pageTotalCount++;
				}
			}
			
		}

		public int getBoardCnt() {
			return boardCnt;
		}

		public void setBoardCnt(int boardCnt) {
			this.boardCnt = boardCnt;
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

		public int getBoardCountperPage() {
			return boardCountperPage;
		}

		public void setBoardCountperPage(int boardCountperPage) {
			this.boardCountperPage = boardCountperPage;
		}

		public List<Board> getBoardList() {
			return boardList;
		}

		public void setBoardList(List<Board> boardList) {
			this.boardList = boardList;
		}

		@Override
		public String toString() {
			return "BoardView [boardCnt=" + boardCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
					+ pageTotalCount + ", firstRow=" + firstRow + ", boardCountperPage="
					+ boardCountperPage + ", boardList=" + boardList + "]";
		}

}
