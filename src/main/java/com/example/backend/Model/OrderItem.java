package com.example.backend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	private double totalProductPrice;	
	private int productQnt;
	
	@OneToOne
	private Product product;
	
	
	@ManyToOne
	private Order order;

	
	
	
	public int getProductQnt() {
		return productQnt;
	}

	public void setProductQnt(int productQnt) {
		this.productQnt = productQnt;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public double getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public OrderItem(int orderItemId, double totalProductPrice, int productQnt, Product product, Order order) {
		super();
		this.orderItemId = orderItemId;
		this.totalProductPrice = totalProductPrice;
		this.productQnt = productQnt;
		this.product = product;
		this.order = order;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}