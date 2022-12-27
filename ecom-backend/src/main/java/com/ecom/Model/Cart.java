package com.ecom.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cart {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
//Relationship with other table

 @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
  private	Set<CartItem> items= new HashSet<>();
 	@OneToOne
 	private User user;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	
	
	
	

}
