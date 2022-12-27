package com.ecom.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity(name = "Orders")
public class Order {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private String orderStatus;
	private String paymentStatus;
	private Date orderDelivered;
	private double orderAmt;
	private String billingAddress;
	private Date orderCreateAt;
	@OneToOne
	private User user;
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderItem> orderItem=new HashSet<>();
	
	//Cons
	
	
	
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, String orderStatus, String paymentStatus, Date orderDelivered, double orderAmt,
			String billingAddress, Date orderCreateAt, User user, Set<OrderItem> orderItem) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.orderDelivered = orderDelivered;
		this.orderAmt = orderAmt;
		this.billingAddress = billingAddress;
		this.orderCreateAt = orderCreateAt;
		this.user = user;
		this.orderItem = orderItem;
	}

	public Date getOrderCreateAt() {
		return orderCreateAt;
	}

	public void setOrderCreateAt(Date orderCreateAt) {
		this.orderCreateAt = orderCreateAt;
	}

	//Getter && Setter
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}
