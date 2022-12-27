package com.ecom.payload;

import java.util.List;

import com.ecom.Model.Product;

public class ProductResponse {
	
	 private List<ProductDto> content;
	 private int pageNumber;
	 private int pageSize;
	 private int totalPages;
	 private boolean lastPage;
	
	public List<ProductDto> getContent() {
		return content;
	}
	public void setContent(List<ProductDto> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	
	 
}
