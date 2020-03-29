package com.myRetail.products.object;

//POJO class for response content formed from retrieving from firebase db and external REST API

public class ProductResponse extends Product{

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProductResponse(String title) {
		super();
		this.title = title;
	}

	public ProductResponse() {
		super();
	}

	public ProductResponse(String id, CurrentPrice current_price) {
		super(id, current_price);
	}
	
}
