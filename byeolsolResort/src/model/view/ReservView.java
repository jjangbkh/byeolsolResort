package model.view;

import java.util.List;

import model.dto.Reserv;

public class ReservView {

		private int reservCnt;
		private int currentPageNum;
		private int pageTotalCount;
		private int firstRow;
		private int reservCountPerPage;
		private List<Reserv> reservList;
	
		public ReservView() {}

		public ReservView(int reservCnt, int currentPageNum, int firstRow, int reservCountPerPage,
				List<Reserv> reservList) {
			super();
			this.reservCnt = reservCnt;
			this.currentPageNum = currentPageNum;
			this.firstRow = firstRow;
			this.reservCountPerPage = reservCountPerPage;
			this.reservList = reservList;
			if(reservCnt>0) {
				pageTotalCount = reservCnt/reservCountPerPage;
				if(reservCnt%reservCountPerPage>0) {
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

		public int getReservCountPerPage() {
			return reservCountPerPage;
		}

		public void setReservCountPerPage(int reservCountPerPage) {
			this.reservCountPerPage = reservCountPerPage;
		}

		public List<Reserv> getReservList() {
			return reservList;
		}

		public void setReservList(List<Reserv> reservList) {
			this.reservList = reservList;
		}

		@Override
		public String toString() {
			return "ReservView [reservCnt=" + reservCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
					+ pageTotalCount + ", firstRow=" + firstRow + ", reservCountPerPage=" + reservCountPerPage
					+ ", reservList=" + reservList + "]";
		}
		
		
}
