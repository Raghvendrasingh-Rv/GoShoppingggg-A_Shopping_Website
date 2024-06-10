package com.example.backend.Payload;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.backend.Model.Cart;
import com.example.backend.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItemDto {
	
	private int cartItemId;
	private int qualtity;
	private int totalPrice;
	@JsonIgnore
	private CartDto cart;
	private ProductDto product;
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQualtity() {
		return qualtity;
	}
	public void setQualtity(int qualtity) {
		this.qualtity = qualtity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CartDto getCart() {
		return cart;
	}
	public void setCart(CartDto cart) {
		this.cart = cart;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public CartItemDto(int cartItemId, int qualtity, int totalPrice, CartDto cart, ProductDto product) {
		super();
		this.cartItemId = cartItemId;
		this.qualtity = qualtity;
		this.totalPrice = totalPrice;
		this.cart = cart;
		this.product = product;
	}
	public CartItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
