package com.example.backend.Payload;


public class OrderRequest {
	
	private int cartId;
	private String orderAddress;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public OrderRequest(int cartId, String orderAddress) {
		super();
		this.cartId = cartId;
		this.orderAddress = orderAddress;
	}
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
