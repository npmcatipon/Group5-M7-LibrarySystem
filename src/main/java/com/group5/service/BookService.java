package com.group5.service;

import java.util.ArrayList;
import java.util.List;

import com.group5.model.Book;
import com.group5.repository.BookRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BookService {
	
	private final BookRepository bookRepository;
	
	private final EntityManager em;
	
	private final List<Book> availableBooks = new ArrayList<>();
	
	public BookService (EntityManager em) {
		this.em = em;
		this.bookRepository = new BookRepository(em);
	}
	
	public List<Book> getAllBooks() {
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		List<Book> books = bookRepository.findAll();
		tx.commit();
		
		return books;
	}

}
