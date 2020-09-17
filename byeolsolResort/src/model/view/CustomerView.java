package model.view;

import java.util.List;

import model.dto.Customer;

public class CustomerView {

	private int customerCnt;
	private int currentPageNum;
	private int pageTotalCount;
	private int firstRow;
	private int customerCountperPage;
	private List<Customer> customerList;
	
	public CustomerView() {}

	public CustomerView(int customerCnt, int currentPageNum, int firstRow, 
			int customerCountperPage, List<Customer> customerList) {
		this.customerCnt = customerCnt;
		this.currentPageNum = currentPageNum;
		this.firstRow = firstRow;
		this.customerCountperPage = customerCountperPage;
		this.customerList = customerList;
		if(customerCnt>0) {
			pageTotalCount = customerCnt/customerCountperPage;
			if(customerCnt%customerCountperPage>0) {
				pageTotalCount++;
			}
		}else {
			pageTotalCount = 0; 
		}
	}

	public int getCustomerCnt() {
		return customerCnt;
	}

	public void setCustomerCnt(int customerCnt) {
		this.customerCnt = customerCnt;
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

	public int getCustomerCountperPage() {
		return customerCountperPage;
	}

	public void setCustomerCountperPage(int customerCountperPage) {
		this.customerCountperPage = customerCountperPage;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> boardList) {
		this.customerList = boardList;
	}

	@Override
	public String toString() {
		return "CustomerView [customerCnt=" + customerCnt + ", currentPageNum=" + currentPageNum + ", pageTotalCount="
				+ pageTotalCount + ", firstRow=" + firstRow + ", customerCountperPage="
				+ customerCountperPage + ", customerList=" + customerList + "]";
	}
	
	

}
