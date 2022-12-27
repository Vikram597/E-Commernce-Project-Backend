package com.ecom.Model;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	private String title;
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Product> product=new HashSet<>();
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int categoryId, String title) {
		super();
		this.categoryId = categoryId;
		this.title = title;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
}
