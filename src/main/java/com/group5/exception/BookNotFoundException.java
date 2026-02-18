package com.group5.exception;

public class BookNotFoundException extends Exception {
	
	public BookNotFoundException(String message) {
		super(message);
	}
	
	public BookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
