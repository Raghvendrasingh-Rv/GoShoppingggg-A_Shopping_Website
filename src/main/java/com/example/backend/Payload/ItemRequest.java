package com.example.backend.Payload;

public class ItemRequest {
	
	private int product_Id;
	private int quantity;
	
	public int getProduct_Id() {
		return product_Id;
	}
	public void setProduct_Id(int product_Id) {
		this.product_Id = product_Id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ItemRequest(int product_Id, int quantity) {
		super();
		this.product_Id = product_Id;
		this.quantity = quantity;
	}
	public ItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
