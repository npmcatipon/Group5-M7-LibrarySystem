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
	
	public BookService (EntityManager em) {
		this.em = em;
		this.bookRepository = new BookRepository(em);
	}
	
	public List<BookDTO> getAllBooks() {
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		allBooks = em.createQuery("select b from Book b",Book.class).getResultList();
		tx.commit();
		
		return allBooks.stream()
				.map(book -> new BookDTO(book))
				.collect(Collectors.toList());
	}

}
