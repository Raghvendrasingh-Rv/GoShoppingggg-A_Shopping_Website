package com.example.backend.Payload;

import java.util.Set;

import com.example.backend.Model.Product;

public class CategoryDto {
	
	private int category_id;
	private String title;
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public CategoryDto(int category_id, String title) {
		super();
		this.category_id = category_id;
		this.title = title;
	}

	private Set<ProductDto> product;
	
	public Set<ProductDto> getProduct(Set<ProductDto> product){
		return product;
	}
	
	public void setProduct(Set<ProductDto> product) {
		this.product=product;
	}

}
