package com.group5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.group5.dto.BookDTO;
import com.group5.model.Book;
import com.group5.repository.BookRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BookService {
	
	private final BookRepository bookRepository;
	
	private final EntityManager em;
	
	private List<Book> allBooks = new ArrayList<>();
	private List<Book> availableBooks = new ArrayList<>();
	private List<Book> borrowedBooks = new ArrayList<>();
	
	public BookService (EntityManager em) {
		this.em = em;
		this.bookRepository = new BookRepository(em);
	}
	
	public List<BookDTO> getAllBooks() {
		
		allBooks = bookRepository.findAll();
		
		return allBooks.stream()
				.map(book -> new BookDTO(book))
				.collect(Collectors.toList());
	}
	
	public List<BookDTO> getAvailableBooks() {
		
		availableBooks = bookRepository.findAvailable();
		
		return availableBooks.stream()
				.map(book -> new BookDTO(book))
				.collect(Collectors.toList());
	}

	public List<BookDTO> getBorrowedBooks() {
	
	borrowedBooks = bookRepository.findBorrowed();
	
	return borrowedBooks.stream()
			.map(book -> new BookDTO(book))
			.collect(Collectors.toList());
	}
	
	public Book findById(Long id) {
		return bookRepository.findById(id);
	}
	
	public Book findBorrowedBookById(Long id) {
		return bookRepository.findBorrowedBookById(id);
	}
	
	public Book updateBookStatus(Book entity, boolean status) {
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Book book = bookRepository.updateBookStatus(entity, status);
		tx.commit();
		
		return book;
	}
	
	public Book addBook(Book entity) {
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		bookRepository.save(entity);
		tx.commit();
		
		return entity;
	}

}
