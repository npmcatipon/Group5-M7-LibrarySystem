package com.group5.exception;

public class InvalidBorrowedBookIdException extends Exception {

		public InvalidBorrowedBookIdException(String message) {
			super(message);
		}
		
		public InvalidBorrowedBookIdException (String message, Throwable cause) {
			super(message, cause);
		}
		
		
}
