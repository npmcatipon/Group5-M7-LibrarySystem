/*
 * 1. Upon application start, ask user to create one User
 * 2. Create one Library object
 * 3. Initialize 5 Book objects and add it to all Library slots
 * 4. Display options:
 * 
 * - [1] Display All Books
 * - [2] Display Available Books
 * - [3] Display All Borrowed Books
 * - [4] Borrow Book
 * - [5] Return Book
 * - [6] Exit
 * 
 * - user selects the number of the option
 * ===============================================
 * 
 *	 [1] Display All Books
 * - Display all Books (ID, Title and Author) regardless if there is a Loan existing for that Book.
 *   
 *   [2] Display Available Books
 * - Display Books that do not have a Loan slot
 * 
 *   [3] Display All Borrowed Books 
 * - Display Books that have a Loan equivalent.
 * - Display the Book title and the User name of borrower
 *   
 *	 [4] Borrow Book
 * - Displays all available books and User selects what book to borrow
 * - Create a Loan object, set Loan id set Book and set User to current user
 * 
 * 	 [5] Return Book
 * - Display all Loans, user selects the Loan and removes that from the slot
 * 
 *   [6] Exit
 * - Stops the program  
 * */

package com.group5.main;

import java.util.Scanner;

//Added import for Logger and LoggerFactory 01.19.2026
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group5.model.User;
import com.group5.model.Book;
import com.group5.model.Loan;
import com.group5.service.BookService;
import com.group5.service.LibraryImpl;
import com.group5.service.LibraryService;
import com.group5.service.LoanService;
import com.group5.service.UserService;
import com.group5.service.impl.BookServiceImpl;
import com.group5.service.impl.LoanServiceImpl;
import com.group5.service.impl.UserServiceImpl;
import com.group5.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

import com.group5.constants.Constants;
import com.group5.exception.BookNotFoundException;
import com.group5.exception.InvalidBookException;
import com.group5.exception.InvalidLoanIdException;
import com.group5.exception.InvalidUserException;
import com.group5.exception.UserCancelException;


public class LibraryApplication {
	private static final Logger logger =  LoggerFactory.getLogger(LibraryApplication.class);
	
	private User user;
	private LibraryService libraryService;
	
	private EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
	
	private UserService userService = new UserServiceImpl(em);
	
	private LoanService loanService = new LoanServiceImpl(em);
	
	private BookService bookService = new BookServiceImpl(em);
	
	public LibraryApplication () {

		this.user = new User();
		this.libraryService = new LibraryImpl();

	}
	
	public void start() {
		
        Scanner input = new Scanner(System.in);

        validateUserLogin(input);
    	
    	welcomeMenuChoice();
    	
    	displayLibraryMenu();
    	askMenuChoice();

    	char option;
        do {
        	
        	option = getMenuChoice(input);
        	
        	System.out.println();
        	//check option
            switch (option) {
            
	            case '1':
	            	//[1] Display All Books
	            	System.out.println(Constants.strDISPLAY_SELECTED_OPTION1);
	            	logger.info("User {} selected option [1] Display All Books", user.getName());
	            	
	            	libraryService.displayAllBooks();
	            	
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;
	
	            case '2':
	            	//[2] Display Available Books
	            	System.out.println(Constants.strDISPLAY_SELECTED_OPTION2);
	            	logger.info("User {} selected option [2] Display Available Books", user.getName());
	            	
	            	libraryService.displayAvailableBooks();
	            	
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;
	
	                
	            case '3':
	            	//[3] Display All Borrowed Books 
	            	System.out.println(Constants.strDISPLAY_SELECTED_OPTION3);
	            	logger.info("User {} selected option [3] Display Borrowed Books", user.getName());

	            	libraryService.displayAllBorrowedBooks();
	            	
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;
	
	                
	            case '4':
	            	//[4] Borrow Book
	            	
	            	displayLibraryMenu();
	            	System.out.println(Constants.strDISPLAY_SELECTED_OPTION4);
	            	logger.info("User {} selected option [4] Borrow Book", user.getName());
	            	
	            	libraryService.displayAvailableBooks();

	            	try {
	            		Book book = validateBookId(input);
	            		
	            		if (book.isBorrowed()) {
	            			throw new InvalidLoanIdException("Book is currently out.");
	            		}
	            		
	            		validateLoanId(input, book);
	            		
	            		System.out.println(user.getName() + " successfully loaned " + book.getTitle());
	            		
	            	} catch (UserCancelException|InvalidLoanIdException e) {
	            		logger.warn(e.getMessage());
	            		System.out.println(e.getMessage());
	            	}
	            	
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;
	
	                
	            case '5':
	            	//[5] Return Book
	            	displayLibraryMenu();
	            	System.out.println(Constants.strDISPLAY_SELECTED_OPTION5);
	            	logger.info("User {} selected option [5] Return Book", user.getName());
	            	
	            	
	            	libraryService.displayAllBorrowedBooks();
	            	
	            	try {
	            		
	            		Loan loan = validateBorrowedBook(input);
	            		logger.info("user {} returned book id: {} with loan id: {} to the library.", user.getName(), loan.getBookId(), loan.getId());
	            		System.out.printf("User %s has returned %s to the library.%n", user.getName(), loan.getBook().getTitle());
	            		
	            	} catch (Exception e) {
	            		System.out.println(e.getMessage());
	            	}
	            	
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;
	                
	                
	            case '6':
	            	//[6] Add Book
	            	
	            	displayLibraryMenu();
	            	System.out.println(Constants.strDISPLAY_SELECTED_OPTION6);
	            	logger.info("User {} selected option [6] Add Book", user.getName());
	            	
	            	try {
	            		
	            		String title = validateTitle(input);
	            		String author = validateAuthor(input);
	            		
	            		Book newBook = new Book();
	            		newBook.setTitle(title);
	            		newBook.setAuthor(author);

	            		bookService.addBook(newBook);
	            		
	            		logger.info("Successfully added {}.", newBook.getTitle());
	            		System.out.println("Successfully added " + newBook.getTitle());
	            		
	            	} catch (UserCancelException e) {
	            		logger.warn(e.getMessage());
	            	} 
	            	
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;

	                
	            case '7':
	            	//[7] Remove Book
	                System.out.println(Constants.strDISPLAY_SELECTED_OPTION7);
	                logger.info("User {} selected option [7] Remove Book", user.getName());
	                
	                
	                libraryService.displayAvailableBooks();
	                
	                try {
	                	
	                	Book book = validateBookId(input);
	                	
	                	bookService.deleteBook(book.getId());
	                	
	                	System.out.println("Book ID: " + book.getId() + " successfully deleted.");
	                	logger.info("User {} deleted book ID: {}.", user.getName(), book.getId());
	                	
	                } catch (UserCancelException e) {
	                	System.out.println(e.getMessage());
	                }
	                
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;

	                
	            case '8':
	            	//[8] Update Book
	                System.out.println(Constants.strDISPLAY_SELECTED_OPTION8);
	                logger.info("User {} selected option [8] Update Book", user.getName());

	                libraryService.displayAvailableBooks();
	                
	                try {
	                	
	                	Book book = validateBookId(input);
	                	
	                	Book updateBook = askUpdateBook(input, book);
	                	
	                	bookService.addBook(updateBook);
	                	
	                	System.out.println("Update of Book ID: " + updateBook.getId() + " is successful.");
	                	
	                	logger.info("User {} updated the book ID: {}.", user.getName(), updateBook.getId());
	                	
	                } catch (UserCancelException e) {
	                	
	                	logger.warn(e.getMessage());
	                }
	                
	            	displayLibraryMenu();
	            	askMenuChoice();
	                break;
	                
	            case '0':
	            	//[0] Exit
	                System.out.println(Constants.strDISPLAY_SELECTED_OPTION0);
	                logger.info("User {} has logged out.", user.getName());
	                break;

	            default:
	            	displayLibraryMenu();
	                System.out.println(Constants.strINVALID_MENU_CHOICE);
	            	askMenuChoice();
	                
            } // end of switch statement

        } while (option != '0');
        

        //close the Scanner
        input.close();
		
	}
	
private Loan validateBorrowedBook(Scanner input) throws UserCancelException {
	
	do {
		try {
			System.out.println(Constants.strPROMPT_ENTER_BOOKID);
			String bookId = input.nextLine();
			
			if (bookId.equalsIgnoreCase("x")) {
				throw new UserCancelException("User " + user.getName() + " selected x. Going back to main menu.");
			}
			
			if (bookId.trim().isEmpty()) {
				throw new InvalidBookException("Book ID cannot be null or empty.");
			}
			
			if (!bookId.matches("\\d+")) {
				throw new NumberFormatException("Book ID must be numeric.");
			}
			
			Loan loan = loanService.findBorrowedBook(Long.valueOf(bookId));
			
			if (loan == null) {
				throw new NullPointerException("Invalid Book ID.");
			}
			
			logger.info("Validating borrowed book with Book ID: {} and Loan ID: {}.", loan.getBookId(), loan.getId());
			
			Book book = bookService.findById(Long.valueOf(bookId));
			bookService.updateReturnBook(book);
			
			return loan;
			
		} catch (InvalidBookException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			
		}
		
	} while (true);
}

private Book askUpdateBook(Scanner input, Book book) 
		throws UserCancelException {
	
	do {
		try {

			System.out.println("Please enter new book title: ");
			String newBookTitle = input.nextLine();

			if (newBookTitle.equalsIgnoreCase("x")) {
				throw new UserCancelException("User " + user.getName() + " selected x. Going back to main menu.");
			}

			if (newBookTitle.trim().isEmpty()) {
				throw new InvalidBookException("Book Title cannot be null or empty.");
			}

			System.out.println("Please enter new book author: ");
			String newBookAuthor = input.nextLine();

			if (newBookAuthor.equalsIgnoreCase("x")) {
				throw new UserCancelException("User {} selected x. Going back to main menu.");
			}

			if (newBookAuthor.trim().isEmpty()) {
				throw new InvalidBookException("Book Title cannot be null or empty.");
			}

			book.setTitle(newBookTitle);
			book.setAuthor(newBookAuthor);
			
			return book;
			
		} catch (InvalidBookException e) {
			System.out.println(e.getMessage());
		}

	} while (true);
	
}

private void validateLoanId(Scanner input, Book book) {
		do {
			
	    	try {
	    		Loan newloan = new Loan();
	    		//newloan.setId(Long.valueOf(loanId));
	    		newloan.setBookId(book.getId());
	    		newloan.setUserId(user.getId());
	    		newloan.setBook(book);
	    		newloan.setUser(user);
	    		
	    		loanService.addLoan(newloan);
	    		logger.info("User {}, adding {} with loan ID: {}.", user.getName(), book.getTitle(), newloan.getId());
	    		
	    		bookService.updateBorrowBook(book);
	    		logger.info("Set {} to borrowed.", book.getTitle());
	    		
	    		break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				logger.warn(e.getMessage());
			}
	    	
		} while (true);

}

private Book validateBookId(Scanner input) throws UserCancelException {
		do {
			try {
				System.out.println(Constants.strPROMPT_ENTER_BOOKID);
		    	String bookId = input.nextLine();
		    	
		    	if (bookId.equalsIgnoreCase("x")) {
		    		logger.error("User {} selected x. Going back to main menu.", user.getName());
		    		throw new UserCancelException(Constants.strERROR_MAIN_MENU);
		    	}
		    	
				if (bookId.trim().isEmpty()) {
					logger.error("Book ID cannot be empty or null.");
					throw new BookNotFoundException(Constants.strERROR_NULL_BOOK_ID);
				}
				
				if (!bookId.matches("\\d+")) {
					logger.error("Book ID must be numeric.");
					throw new NumberFormatException(Constants.strERROR_BOOK_ID_MUST_BE_NUMERIC);
				}
				
				logger.info("User {} searched for Book ID: {}", user.getName(), bookId);
				
				Book findBookId = bookService.findById(Long.valueOf(bookId));
				
				if (findBookId == null) {
					logger.error("Invalid Book ID number.");
					throw new BookNotFoundException(Constants.strERROR_INVALID_BOOK_ID);
				}
				
				return findBookId;
		    	
			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			
		} while (true);
		
	}

	private String validateAuthor(Scanner input) throws UserCancelException {
		
		do {
			
			try {
				
				System.out.println(Constants.strPROMPT_ENTER_BOOKAUTHOR);
				String author = input.nextLine();
				
				if (author.trim().isEmpty() || author == null) {
					logger.warn("Author cannot be null.");
					throw new InvalidBookException(Constants.strERROR_NULL_AUTHOR);
				}
				
				if (author.equalsIgnoreCase("x")) {
					logger.warn("User {} selected x. Going back to main menu.", user.getName());
					throw new UserCancelException(Constants.strERROR_MAIN_MENU);
				}
				
				return author;
			} catch (InvalidBookException e) {
				logger.info(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		} while (true);
	}

	private String validateTitle(Scanner input) throws UserCancelException {
		
		do {
			
			try {
				
				System.out.println(Constants.strPROMPT_ENTER_BOOKTITLE);
				String title = input.nextLine();

				if (title.trim().isEmpty() || title == null) {
					logger.warn("Title cannot be null.");
					throw new InvalidBookException("Title cannot be null or empty");
				}

				if (title.equalsIgnoreCase("x")) {
					logger.warn("User {} selected x. Going back to main menu.", user.getName());
					throw new UserCancelException(Constants.strERROR_MAIN_MENU);
				}
				
				return title;
				
			} catch (InvalidBookException e) {
				logger.info(e.getMessage());
				System.out.println(e.getMessage());
			}

		} while (true);

	}

	private void validateUserLogin(Scanner input) {
		
		boolean login = false;
		
		do {
			try {
				System.out.println(Constants.strPROMPT_USERID);
				String userID = input.nextLine().trim();
				
				if (userID.trim().isEmpty() || userID == null) {
					throw new InvalidUserException("User ID is null or empty.");
				}
				
				if (!userID.matches("\\d+")) {
					throw new InvalidUserException("User ID must be numeric.");
				}
				
				System.out.println(Constants.strPROMPT_USERNAME);
				String username = input.nextLine().trim();
				
				if (username.trim().isEmpty() || username == null) {
					logger.warn("Username is not valid");
					throw new InvalidUserException("Username is null or empty.");
				}
				
				User userLogin = userService.findById(Long.valueOf(userID));
				
				if (userLogin == null) { 
					logger.error("Invalid User ID and/or Username.");
					throw new InvalidUserException("Invalid User ID and/or Username.");
				}
				
				if (!userLogin.getName().equals(username)) {
					throw new InvalidUserException("Username does not exist.");
				}
				
				user = userLogin;
				login = true;
				logger.info("{} successfully login.", user.getName());
				
			} catch (InvalidUserException e) {
				System.out.println(e.getMessage());
				logger.error(e.getMessage());
			}

		} while (!login);
		
	}



	private void displayLibraryMenu() {
		System.out.println(Constants.strDISPLAY_MENU);
	}
	
	
	private void welcomeMenuChoice() {
    	System.out.print  (" Hello, " + user.getName() + "!");
	}
	
	private void askMenuChoice() {
    	System.out.print(Constants.strPROMPT_CHOICE);
	}

	private char getMenuChoice (Scanner input) {
		String tempInput = input.nextLine().trim();
    	char option;
    	//check the encoded option
    	if ((tempInput == null) || (tempInput.length() != 1) ) {
    		//set to X to make it an invalid choice
    		option = "X".charAt(0);
    	} else {
    		option = tempInput.charAt(0);
    	} 
    	
    	return option;
	}
	
}
