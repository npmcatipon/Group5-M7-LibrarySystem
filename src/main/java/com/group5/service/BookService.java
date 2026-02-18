package com.group5.service;

import java.util.List;

import com.group5.model.Book;

public interface BookService {
	
	List<Book> getAllBooks();

	List<Book> getAvailableBooks();

	void addBook(Book book);

	Book findById(Long id);

	void updateBorrowBook(Book book);

	void updateReturnBook(Book book);

	void deleteBook(Long id);

	void updateBook(Book book);
}
