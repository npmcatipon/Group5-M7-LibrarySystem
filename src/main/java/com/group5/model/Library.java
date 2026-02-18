package com.group5.model;

import java.util.List;

public class Library {
    // can contain up to 5 books	

	private List<Book> bookList;
	private List<Loan> loanList;
	
	
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public List<Loan> getLoanList() {
		return loanList;
	}
	public void setLoanList(List<Loan> loanList) {
		this.loanList = loanList;
	}
	
}
