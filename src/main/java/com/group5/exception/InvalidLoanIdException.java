package com.group5.exception;

public class InvalidLoanIdException extends Exception {

	public InvalidLoanIdException(String messsage) {
		super(messsage);
	}
	
	public InvalidLoanIdException(String message, Throwable cause) {
		super(message, cause);
	}
}
