package com.example.backend.Payload;

import com.example.backend.Model.Category;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class ProductDto {
	
	private int product_id;
	private String product_name;
	private int product_price;
	private boolean stock;
	private int product_quantity;
	private boolean live;
	private String product_imageName;
	private String product_description;
	
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	private CategoryDto category;

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public String getProduct_imageName() {
		return product_imageName;
	}

	public void setProduct_imageName(String product_imageName) {
		this.product_imageName = product_imageName;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public ProductDto(int product_id, String product_name, int product_price, boolean stock, int product_quantity,
			boolean live, String product_imageName, String product_description) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.stock = stock;
		this.product_quantity = product_quantity;
		this.live = live;
		this.product_imageName = product_imageName;
		this.product_description = product_description;
	}


}
