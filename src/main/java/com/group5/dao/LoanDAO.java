package com.group5.dao;

import com.group5.exception.DuplicateLoanIdException;
import com.group5.exception.InvalidBorrowedBookIdException;
import com.group5.model.Loan;

public interface LoanDAO {

	String findLoanId(String loanId) throws DuplicateLoanIdException;

	void addLoanBook(String loanId, String userId, String bookId);

	Loan findReturnBookId(String loanId) throws InvalidBorrowedBookIdException;

	void deleteLoanId(String loanId);

}
