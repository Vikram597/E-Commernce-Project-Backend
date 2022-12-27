package com.ecom.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.ecom.Model.*;

@Entity
@Table(name="Product")   //@Table we can table name
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int    productId;
	private String productName;
	private String productDesc;
	private double productPrize;
	private boolean stock=true;
	private int productQuantity;
	private boolean live;
	private String imageName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getProductPrize() {
		return productPrize;
	}

	public void setProductPrize(double productPrize) {
		this.productPrize = productPrize;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
}
