package com.ecom.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Role {
    @Id
	private int id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	Set<User> user=new HashSet<>();
	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Role(int id, String name, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
