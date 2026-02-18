package com.group5.repository;

import java.util.List;

import com.group5.model.Book;

import jakarta.persistence.EntityManager;

public class BookRepository implements Repository<Book, Long> {

	private final EntityManager em;
	
	public BookRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Book save(Book entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Book entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll() {
		return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
	}
	
}
