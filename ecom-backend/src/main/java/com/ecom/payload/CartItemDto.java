package com.ecom.payload;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ecom.Model.Cart;
import com.ecom.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItemDto {
	private int cartItemId;
	private int quantity;
	private double totalprice;
	@JsonIgnore
	private CartDto cart;
	private ProductDto product;
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	public CartDto getCart() {
		return cart;
	}
	public void setCart(CartDto cart) {
		this.cart = cart;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	
	
	
}
