package com.group5.exception;

public class UserCancelException extends Exception {
	
	public UserCancelException(String message) {
		super(message);
	}
	
	public UserCancelException(String message, Throwable cause) {
		super(message, cause);
	}

}
