package com.group5.service.impl;

import java.util.List;

import com.group5.model.Loan;
import com.group5.repository.impl.LoanRepositoryImpl;
import com.group5.service.LoanService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LoanServiceImpl implements LoanService {
	
	private EntityManager em;
	
	private LoanRepositoryImpl loanRepository;
	
	public LoanServiceImpl (EntityManager em) {
		this.em = em;
		this.loanRepository = new LoanRepositoryImpl(em);
	}

	@Override
	public Loan findById(Long id) {
		
		return loanRepository.findById(id);
		
	}

	@Override
	public void deleteLoanId(Loan loan) {
		
		loanRepository.delete(loan);
		
	}

	@Override
	public void addLoan(Loan loan) {
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			
			tx.begin();
			
			loanRepository.save(loan);
			
			tx.commit();
			
		} catch (Exception e) {
			
			if (tx.isActive()) {
				
				tx.rollback();
				
			}
			
			System.out.println(e.getMessage());
			
			throw e;
		}
		
		
		
	}
	
	public Loan findBorrowedBook (Long bookid) {
		
		return loanRepository.findTopBorrowBookId(bookid);
	}

	@Override
	public List<Loan> getBorrowedBooks() {
		return loanRepository.displayBorrowedBook();
	}
	
}
