package com.myRetail.products.controller;

import java.util.concurrent.ExecutionException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.myRetail.products.exception.ProductIDConflictException;
import com.myRetail.products.object.Acknowledgement;
import com.myRetail.products.object.Product;
import com.myRetail.products.object.ProductResponse;
import com.myRetail.products.service.FirebaseService;
import com.myRetail.products.util.ValidateContent;

@RestController
public class ProductController {

	@Autowired
	FirebaseService firebaseService;

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponse> getProductDetails(@PathVariable String id)
			throws InterruptedException, ExecutionException {
		ProductResponse productResponse = firebaseService.getProductDetails(id);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}

	@PostMapping("/products/createProduct")
	public Acknowledgement postProductDetails(@Valid @RequestBody Product product)
			throws InterruptedException, ExecutionException {
		ValidateContent.validationContent(product);
		Acknowledgement postedProduct = firebaseService.saveProductDetails(product);
		if (postedProduct == null) {
			throw new ProductIDConflictException("Product ID already exists");
		}
		return postedProduct;
	}

	@PutMapping("/products/{id}")
	public Acknowledgement updateProductDetails(@PathVariable String id, @RequestBody Product product)
			throws InterruptedException, ExecutionException {
		ValidateContent.validationContent(id, product);
		Acknowledgement postedProduct = firebaseService.updateProductDetails(id, product);
		return postedProduct;
	}
}