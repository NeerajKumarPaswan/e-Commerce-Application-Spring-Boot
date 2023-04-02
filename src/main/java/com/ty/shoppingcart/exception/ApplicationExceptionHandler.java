package com.ty.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.shoppingcart.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(value = NoIdFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noIdFoundExceptionHandler(NoIdFoundException ie){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("No Id Found");
		responseStructure.setData(ie.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> invalidCredentialsExceptionHandler(InvalidCredentialsException ic){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invlaid");
		responseStructure.setData(ic.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	}
}
