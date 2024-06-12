package com.example.backend.Payload;

import java.util.List;

public class ProductResponse {
	
	private List<ProductDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalpages;
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
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public ProductResponse(List<ProductDto> content, int pageNumber, int pageSize, int totalpages, boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalpages = totalpages;
		this.lastPage = lastPage;
	}
	public ProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
