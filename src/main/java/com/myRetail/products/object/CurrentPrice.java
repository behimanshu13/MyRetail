package com.myRetail.products.object;

public class CurrentPrice {
	private float price;
	private String currency_code;
	
	public CurrentPrice() {
		super();
	}
	public CurrentPrice(float price, String currency_code) {
		super();
		this.price = price;
		this.currency_code = currency_code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	
	
	
}

