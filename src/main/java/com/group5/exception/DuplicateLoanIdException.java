package com.group5.exception;

public class DuplicateLoanIdException extends Exception {

	public DuplicateLoanIdException (String message) {
		super(message);
	}
	
	public DuplicateLoanIdException(String message, Throwable cause) {
		super(message, cause);
	}
}
