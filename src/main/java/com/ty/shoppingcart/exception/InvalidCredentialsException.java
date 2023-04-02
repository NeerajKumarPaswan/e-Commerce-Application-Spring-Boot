package com.ty.shoppingcart.exception;

public class InvalidCredentialsException extends RuntimeException {
	
	private String message = "Email or password Wrong";
	public InvalidCredentialsException() {}
	public InvalidCredentialsException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
}
