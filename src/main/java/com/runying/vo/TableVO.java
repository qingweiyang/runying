package com.runying.vo;

import java.util.List;

public class TableVO <T> {
	private Long pages;
	
	private int currentPage;
	
	private int countPerPage;
	
	private List<T> rows;

	public Long getPages() {
		return pages;
	}

	public void setPages(long l) {
		this.pages = l;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
