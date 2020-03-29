package com.myRetail.products.exception;

public class ProductIDConflictException extends RuntimeException{
	public ProductIDConflictException(String message) {
		super(message);
	}
}
