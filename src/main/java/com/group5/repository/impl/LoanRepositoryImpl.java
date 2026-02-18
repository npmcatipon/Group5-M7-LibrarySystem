package com.group5.repository.impl;

import java.util.List;

import com.group5.model.Loan;
import com.group5.repository.Repository;

import jakarta.persistence.EntityManager;

public class LoanRepositoryImpl implements Repository <Loan, Long> {

	private final EntityManager em;

	public LoanRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Loan save(Loan loan) {
		
		if ( loan.getId() == null ) {
			em.persist(loan);
		} else {
			em.merge(loan);
		}
		
		return loan;
	}

	@Override
	public void delete(Loan loan) {
		
		em.remove(em.contains(loan) ? loan : em.merge(loan));
		
	}

	@Override
	public void deleteById(Long id) {
		
		Loan loan = findById(id);
		
		if( loan != null ) {
			delete(loan);
		}
		
	}

	@Override
	public Loan findById(Long id) {
		
		return em.find(Loan.class, id);
		
	}

	@Override
	public List<Loan> findAll() {
		
		return em.createQuery("SELECT l FROM loan l", Loan.class).getResultList();
		
	}
	
	public Loan findTopBorrowBookId(Long bookId) {
		List <Loan> loan = em.createQuery(
				"select l from Loan l " +
				"join fetch l.book b " +
				"join fetch l.user u " +
				"where b.id = :bookid " +
				"order by l.id desc",
				Loan.class)
				.setParameter("bookid", bookId)
				.getResultList();
		
		System.out.println(loan.get(0).getBookId());
		
		return loan.isEmpty() ? null : loan.get(0);
	}

	public List<Loan> displayBorrowedBook() {
		List<Loan> loan = em.createQuery(
				"select l from Loan l " +
				"join fetch l.book b " +
				"join fetch l.user u " +
				"where b.isBorrowed = true and " +
				"l.id = (select max(l2.id) from Loan l2 where l2.book = b)",
				Loan.class)
				.getResultList();
		return loan;
	}


}
