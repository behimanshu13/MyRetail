package com.myRetail.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.myRetail.products.object.ErrorMessage;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 500);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<Object> handleProductNotFoundException(Exception ex, WebRequest request){
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 404);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> handleDuplicateEntryRequest(Exception ex, WebRequest request){
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 400);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ProductIDConflictException.class)
	public final ResponseEntity<Object> handleProductIDConflictRequest(Exception ex, WebRequest request){
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 409);
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
}	
