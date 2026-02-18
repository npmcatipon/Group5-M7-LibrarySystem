package com.group5.exception;

public class InvalidBookException extends Exception {
	
	public InvalidBookException(String message) {
		super(message);
	}

	public InvalidBookException(String message, Throwable cause) {
		super(message, cause);
	}

}
