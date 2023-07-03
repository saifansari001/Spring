package com.dao;
import javax.persistence.*;
import java.util.*;
@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	private String hashedPassword;
	
	@OneToMany(mappedBy="user_obj")
	private List<Product> product;
	
	public User()
	{
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
}
