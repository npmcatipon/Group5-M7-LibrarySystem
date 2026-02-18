package com.group5.dao;

import java.util.List;

import com.group5.model.Book;

public interface BookDAO {

	List<Book> getAllBooks();

	List<Book> getAvailableBooks();

	List<Book> getBorrowedBooks();

	void addBook(String title, String author);

	Book findById(String bookId);

	void updateBorrowBook(String bookId);

	void updateReturnBook(String bookId);

	void deleteBook(String bookId);

	void updateBook(Book book);

}
