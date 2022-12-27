package com.ecom.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ecom.Model.OrderItem;
import com.ecom.Model.User;

public class OrderDto {
	
	private int orderId;
	private String orderStatus;
	private String paymentStatus;
	private Date orderDelivered;
	private double orderAmt;
	private String billingAddress;
	
	private UserDto user;
	
	private Set<OrderItemDto> orderItem=new HashSet<>();

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getOrderDelivered() {
		return orderDelivered;
	}

	public void setOrderDelivered(Date orderDelivered) {
		this.orderDelivered = orderDelivered;
	}

	public double getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(double orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Set<OrderItemDto> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItemDto> orderItem) {
		this.orderItem = orderItem;
	}
	
	
	
}
