package com.ecom.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.ecom.Model.*; 

@Entity
public class OrderItem {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	@OneToOne
	private Product product;
	
	private double totalProductprice;
	
	private int productQuantity;
	@ManyToOne
	private Order order;
	
	
	
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public OrderItem(int orderItemId, Product product, double totalProductprice, int productQuantity, Order order) {
		super();
		this.orderItemId = orderItemId;
		this.product = product;
		this.totalProductprice = totalProductprice;
		this.productQuantity = productQuantity;
		this.order = order;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getTotalProductprice() {
		return totalProductprice;
	}
	public void setTotalProductprice(double totalProductprice) {
		this.totalProductprice = totalProductprice;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
