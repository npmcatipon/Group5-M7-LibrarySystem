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

		if (entity.getId() == null) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		
		return entity;
		
	}

	@Override
	public void delete(Book entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public void deleteById(Long id) {
		
		Book book = findById(id);
		
		if (book != null ) {
			delete(book);
		}
	}

	@Override
	public Book findById(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
	}
	
	public List<Book> findAvailable() {
		return em.createQuery("SELECT b FROM Book b WHERE b.isBorrowed = false", Book.class).getResultList();
				
	}
	
	public List<Book> findBorrowed() {
		return em.createQuery("SELECT b FROM Book b WHERE b.isBorrowed = true", Book.class).getResultList();
	}
	
	public Book updateBookStatus(Book entity, boolean status) {
		entity.setIsBorrowed(status);
		return save(entity);
	}
}
