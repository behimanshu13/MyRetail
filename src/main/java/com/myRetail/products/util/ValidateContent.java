package com.myRetail.products.util;

import org.springframework.stereotype.Service;

import com.myRetail.products.exception.BadRequestException;
import com.myRetail.products.object.Product;

@Service
public class ValidateContent {

	public static void validationContent(Product product) {
		if(product.getId()==null) {
			throw new BadRequestException("Product ID cannot be null");
		}
		if(product.getCurrent_price()==null) {
			throw new BadRequestException("Enter pricing details");
		}
		if(product.getCurrent_price().getCurrency_code()==null || product.getCurrent_price().getCurrency_code().length()!=3) {
			throw new BadRequestException("Currency code entered is invalid");
		}
		if(product.getCurrent_price().getPrice()<0) {
			throw new BadRequestException("Price cannot be negative");
		}
	}
	
	public static void validationContent(String id, Product product) {
		if(!id.equals(product.getId())) {
			throw new BadRequestException("Product ID in parameter and request body should be same");
		}
		if(product.getId()==null) {
			throw new BadRequestException("Product ID cannot be null");
		}
		if(product.getCurrent_price()==null) {
			throw new BadRequestException("Enter pricing details");
		}
		if(product.getCurrent_price().getCurrency_code()==null || product.getCurrent_price().getCurrency_code().length()!=3) {
			throw new BadRequestException("Currency code entered is invalid");
		}
		if(product.getCurrent_price().getPrice()<0) {
			throw new BadRequestException("Price cannot be negative");
		}
	}
	
}
