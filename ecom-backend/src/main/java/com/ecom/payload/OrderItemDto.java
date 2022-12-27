package com.ecom.payload;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ecom.Model.Order;
import com.ecom.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderItemDto {

	private int orderItemId;
	
	private ProductDto product;
	
	private double totalProductprice;
	@JsonIgnore
	private OrderDto order;

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public double getTotalProductprice() {
		return totalProductprice;
	}

	public void setTotalProductprice(double totalProductprice) {
		this.totalProductprice = totalProductprice;
	}

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}
	
	
	
}
