package com.myRetail.products.object;

//POJO class for storing product details into firebase db

public class Product {

	private String id;
	private CurrentPrice current_price;
	
	public Product() {
		super();
	}
	
	public Product(String id, CurrentPrice current_price) {
		super();
		this.id = id;
		this.current_price = current_price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CurrentPrice getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(CurrentPrice current_price) {
		this.current_price = current_price;
	}
	
}
