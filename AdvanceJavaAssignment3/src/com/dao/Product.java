package com.dao;
import java.util.Base64;

import javax.persistence.*;

@Entity
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne @JoinColumn(name="user_id", nullable=false)
	private User user_obj;
	private String Title;
	private long quantity;
	private int size;
	private byte[] image;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user_obj;
	}
	public void setUser(User user) {
		this.user_obj = user;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getImage() {
		
		return Base64.getEncoder().encodeToString(image);
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getImageByte()
	{
		return image;
	}
	public long getImageByteSize()
	{
		return image.length;
	}
}
