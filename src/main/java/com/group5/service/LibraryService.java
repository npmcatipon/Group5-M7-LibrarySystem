package com.group5.service;

public interface LibraryService {
	
	public void displayAllBooks();
	
	public void displayAvailableBooks();
	
	public void displayAllBorrowedBooks();
	
	public static int displayTableDetails(int displayType) {
		return 0;
	}
	
	public static void displayTableHeader(int displayType) {
	}
	
}
