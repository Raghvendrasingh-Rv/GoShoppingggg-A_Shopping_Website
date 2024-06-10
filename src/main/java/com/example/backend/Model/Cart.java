package com.example.backend.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> cartItem = new HashSet<>();
	
	@OneToOne
	private User user;

	public Set<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	
	public Cart(int cartId) {
		super();
		this.cartId = cartId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public Cart(int cartId, Set<CartItem> cartItem, User user) {
		super();
		this.cartId = cartId;
		this.cartItem = cartItem;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
