package com.example.backend.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int category_id;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Product> product;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategory_id() {
		return category_id;
	}
	public Category(int category_id, String title) {
		super();
		this.category_id = category_id;
		this.title = title;
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
	private String title;
	
	public Set<Product> getProduct(Set<Product> product){
		return product;
	}
	
	public void setProduct(Set<Product> product) {
		this.product=product;
	}
 
}
