package com.myRetail.products.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.anyString;
import com.myRetail.products.exception.DataNotFoundException;
import com.myRetail.products.object.CurrentPrice;
import com.myRetail.products.object.ProductResponse;
import com.myRetail.products.service.FirebaseService;

class ProductControllerTest {
	
	
//Instantiated controller class and injects mock annotations into it	
	@InjectMocks
	ProductController productController;
	
	@Mock
	FirebaseService firebaseService;

	ProductResponse productResponse;
	CurrentPrice current_price;
	
	final String user_id = "13860431";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		current_price = new CurrentPrice();
		current_price.setPrice((float) 644.49);
		current_price.setCurrency_code("INR");

		productResponse = new ProductResponse();
		productResponse.setId(user_id);
		productResponse.setTitle("The Hunters (DVD)");
		productResponse.setCurrent_price(current_price);
	}

	@Test
	final void testGetProductDetails() throws InterruptedException, ExecutionException{
		when(firebaseService.getProductDetails(anyString())).thenReturn(productResponse);
		ResponseEntity<ProductResponse> finalResponse = productController.getProductDetails(user_id);
		assertNotNull(finalResponse);
		assertEquals(user_id, finalResponse.getBody().getId());
		assertEquals(productResponse.getTitle(), finalResponse.getBody().getTitle());
		assertEquals(productResponse.getCurrent_price(), finalResponse.getBody().getCurrent_price());
	}
	
	@Test
	final void testGetProductDetails_ProductNotFoundException() throws InterruptedException, ExecutionException{
			when(firebaseService.getProductDetails(anyString())).thenThrow(DataNotFoundException.class);
			assertThrows(DataNotFoundException.class, () -> {
			productController.getProductDetails(user_id);
	    });
	}
	
}