package com.example.backend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CartItem")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartItemId;
	private int qualtity;
	private double totalPrice;
	
	@ManyToOne
	private Cart cart;
	
	@OneToOne
	private Product product;
	
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public CartItem(int cartItemId, int qualtity, double totalPrice, Cart cart, Product product) {
		super();
		this.cartItemId = cartItemId;
		this.qualtity = qualtity;
		this.totalPrice = totalPrice;
		this.cart = cart;
		this.product = product;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
