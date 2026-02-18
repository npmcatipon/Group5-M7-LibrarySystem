package com.group5.service;

import com.group5.model.Book;
import com.group5.model.Library;
import com.group5.model.Loan;
import com.group5.service.impl.BookServiceImpl;
import com.group5.service.impl.LoanServiceImpl;
import com.group5.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

import com.group5.constants.Constants;

public class LibraryImpl implements LibraryService {
	
	public Library library;
	
	private EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
	
	private BookService bookService = new BookServiceImpl(em);
	private LoanService loanService = new LoanServiceImpl(em);

	private static final int DISPLAY_ALL_BOOKS       =  1;
	private static final int DISPLAY_AVAILABLE_BOOKS =  2;
	private static final int DISPLAY_BORROWED_BOOKS  =  3;
	private static final int DISPLAY_ALL_LOANS       =  4;
	
	@Override
	public void displayAllBooks() {
		displayTableHeader(DISPLAY_ALL_BOOKS);
	}


	@Override
	public void displayAvailableBooks() {
		displayTableHeader(DISPLAY_AVAILABLE_BOOKS);
	}


	@Override
	public void displayAllBorrowedBooks() {
		displayTableHeader(DISPLAY_BORROWED_BOOKS);
    }


	private void displayTableHeader(int displayType) {
		//header

		displayTableLine(displayType);

		if (displayType == DISPLAY_ALL_BOOKS || displayType == DISPLAY_AVAILABLE_BOOKS) {
			System.out.println("" +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_ID,       Constants.maxLenBookId,     " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_TITLE,    Constants.maxLenBookTitle,  " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_AUTHOR,   Constants.maxLenBookAuthor, " ") +
					Constants.strTableColumnDelimiter);

		} else if (displayType == DISPLAY_BORROWED_BOOKS) {
			System.out.println("" +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_ID,        Constants.maxLenBookId,     " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_TITLE,     Constants.maxLenBookTitle,  " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_AUTHOR,    Constants.maxLenBookAuthor, " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_BORROWER,  Constants.maxLenUserName,   " ") +
					Constants.strTableColumnDelimiter);

		} else if (displayType == DISPLAY_ALL_LOANS) {
			System.out.println("" +
					Constants.strTableColumnDelimiter + padRight(Constants.strLOAN_COLUMN_ID,        Constants.maxLenBookId,     " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_TITLE,     Constants.maxLenBookTitle,  " ") +
					Constants.strTableColumnDelimiter + padRight(Constants.strBOOK_COLUMN_BORROWER,  Constants.maxLenUserName,   " ") +
					Constants.strTableColumnDelimiter);

		}
		
		displayTableLine(displayType);
		
		displayTableDetails(displayType);
		
		displayTableLine(displayType);

	}


	/*
	 * parameter input
	 * displayType:
	 * 1 - all books
	 * 2 - available books
	 * 3 - borrowed books
	 *
	 */
	private void displayTableDetails(int displayType) {
		if (displayType == DISPLAY_ALL_BOOKS) {
			for (Book b: bookService.getAllBooks()) {
				System.out.println("" +
						Constants.strTableColumnDelimiter + padRight(String.valueOf(b.getId()),     Constants.maxLenBookId,     " ") +
						Constants.strTableColumnDelimiter + padRight(b.getTitle(),  Constants.maxLenBookTitle,  " ") +
						Constants.strTableColumnDelimiter + padRight(b.getAuthor(), Constants.maxLenBookAuthor, " ") +
						Constants.strTableColumnDelimiter);
			}
		} else if (displayType == DISPLAY_AVAILABLE_BOOKS) {
	    	for (Book b: bookService.getAvailableBooks()) {
	    		System.out.println("" +
						Constants.strTableColumnDelimiter + padRight(String.valueOf(b.getId()),     Constants.maxLenBookId,     " ") +
						Constants.strTableColumnDelimiter + padRight(b.getTitle(),  Constants.maxLenBookTitle,  " ") +
						Constants.strTableColumnDelimiter + padRight(b.getAuthor(), Constants.maxLenBookAuthor, " ") +
						Constants.strTableColumnDelimiter);
	    	}
		} else if (displayType == DISPLAY_BORROWED_BOOKS) {
			for (Loan l: loanService.getBorrowedBooks()) {
				System.out.println("" +
						Constants.strTableColumnDelimiter + padRight(String.valueOf(l.getBookId()),     Constants.maxLenBookId,     " ") +
						Constants.strTableColumnDelimiter + padRight(l.getBook().getTitle(),  Constants.maxLenBookTitle,  " ") +
						Constants.strTableColumnDelimiter + padRight(l.getBook().getAuthor(), Constants.maxLenBookAuthor, " ") +
						Constants.strTableColumnDelimiter + padRight(l.getUser().getName(), Constants.maxLenUserName,   " ") +
						Constants.strTableColumnDelimiter);
	    	}
			
		}
			
	}


	private void displayTableLine (int displayType) {
		//header

		if (displayType == DISPLAY_ALL_BOOKS || displayType == DISPLAY_AVAILABLE_BOOKS) {
			System.out.println(" " +
					padRight("", Constants.maxLenBookId + Constants.strTableColumnDelimiter.length(),     "=") +
					padRight("", Constants.maxLenBookTitle + Constants.strTableColumnDelimiter.length(),  "=") +
					padRight("", Constants.maxLenBookAuthor + Constants.strTableColumnDelimiter.length(), "=") +
					padRight("", 1, "=")
					);
		} else if (displayType == DISPLAY_BORROWED_BOOKS) {
			System.out.println(" " +
					padRight("", Constants.maxLenBookId + Constants.strTableColumnDelimiter.length(),     "=") +
					padRight("", Constants.maxLenBookTitle + Constants.strTableColumnDelimiter.length(),  "=") +
					padRight("", Constants.maxLenBookAuthor + Constants.strTableColumnDelimiter.length(), "=") +
					padRight("", Constants.maxLenUserName + Constants.strTableColumnDelimiter.length(),   "=") +
					padRight("", 1, "=")
					);
		} else if (displayType == DISPLAY_ALL_LOANS) {
			System.out.println(" " +
					padRight("", Constants.maxLenLoanId + Constants.strTableColumnDelimiter.length(),    "=") +
					padRight("", Constants.maxLenBookTitle + Constants.strTableColumnDelimiter.length(), "=") +
					padRight("", Constants.maxLenUserName + Constants.strTableColumnDelimiter.length(),  "=") +
					padRight("", 1, "=")
					);
		}
	}

	private static String padRight(String param, int length, String padValue) {
		String returnValue = "";
		String temp  = "";

		if (param.equals("")) {
			temp = param;

			for (int i = 0; i < length; i++) {
				temp = padValue + "" + temp;
			}
			returnValue = temp;

		}

		int difference = length - param.length();

		if (param.length() < length) {
			temp = param;
			for (int i = 0; i < difference; i++) {
				temp += padValue;
			}
			returnValue = temp;

		} else if (param.length() > length) {
			returnValue = param.substring(0, length);

		} else if (param.length() == length) {
			returnValue = param;
		}

		return returnValue;
	}






}
