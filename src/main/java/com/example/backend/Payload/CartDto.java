package com.example.backend.Payload;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.backend.Model.CartItem;
import com.example.backend.Model.User;

public class CartDto {
	
	private int cartId;
	private Set<CartItemDto> cartItem = new HashSet<>();
	private UserDto user;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Set<CartItemDto> getCartItem() {
		return cartItem;
	}
	public void setCartItem(Set<CartItemDto> cartItem) {
		this.cartItem = cartItem;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public CartDto(int cartId, Set<CartItemDto> cartItem, UserDto user) {
		super();
		this.cartId = cartId;
		this.cartItem = cartItem;
		this.user = user;
	}
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
