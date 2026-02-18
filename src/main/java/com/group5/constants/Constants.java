package com.group5.constants;

public abstract class Constants {

	//login/menu
	public static final String strPROMPT_USERID   = " Enter user ID:  > ";
	public static final String strPROMPT_USERNAME = " Enter username: > ";
	
	public static final String strPROMPT_CHOICE = " Please enter your choice: > ";
	
	public static final String strDISPLAY_SELECTED_OPTION1 = "\n You have selected Option 1 - Display All Books";

	public static final String strDISPLAY_SELECTED_OPTION2 = "\n You have selected Option 2 - Display Available Books";

	public static final String strDISPLAY_SELECTED_OPTION3 = "\n You have selected Option 3 - Display All Borrowed Books";

	public static final String strDISPLAY_SELECTED_OPTION4 = "\n You have selected Option 4 - Borrow Book";

	public static final String strDISPLAY_SELECTED_OPTION5 = "\n You have selected Option 5 - Return Book";
	
	public static final String strDISPLAY_SELECTED_OPTION6 = "\n You have selected Option 6 - Add Book";
	
	public static final String strDISPLAY_SELECTED_OPTION7 = "\n You have selected Option 7 - Remove Book";

	public static final String strDISPLAY_SELECTED_OPTION8 = "\n You have selected Option 8 - Update Book";

	public static final String strDISPLAY_SELECTED_OPTION0 = "\n Thank you for using the system!";

	public static final String strDISPLAY_MENU = 
					("\n " +
					"\n ****************************************** WELCOME TO GROUP 5 LIBRARY SYSTEM ****************************************" + 
					"\n *                                                                                                                   *" + 
					"\n *             [1] Display All Books                      [6] Add Book                                               *" + 
					"\n *             [2] Display Available Books                [7] Remove Book                                            *" + 
					"\n *             [3] Display All Borrowed Books             [8] Update Book                                            *" + 
					"\n *             [4] Borrow Book                            [0] Exit                                                   *" + 
					"\n *             [5] Return Book                                                                                       *" + 
					"\n *                                                                                                                   *" + 
					"\n *********************************************************************************************************************");

	
	public static final String strBOOK_COLUMN_ID       = "Book ID";
	public static final String strBOOK_COLUMN_TITLE    = "Book Title";
	public static final String strBOOK_COLUMN_AUTHOR   = "Author";
	public static final String strBOOK_COLUMN_BORROWER = "Borrower";
	
	public static final String strLOAN_COLUMN_ID       = "Loan ID";
	
	
	
	//book table
	public final static Integer maxLenBookId = 7;
	public final static Integer maxLenBookTitle = 50;
	public final static Integer maxLenBookAuthor = 50;
	public final static String  strTableColumnDelimiter = " | ";
	
	
	public final static Integer maxLenUserName = 30;
	
	
	//loan table
	public final static Integer maxLenLoanId = 7;
	

	//option 1
	public static final String strDISPLAY_ALL_BOOKS       = " Displaying all the books... \n";
	
	//option 2
	public static final String strDISPLAY_AVAILABLE_BOOKS = " Displaying available books... \n";
	
	//option 3
	public static final String strDISPLAY_BORROWED_BOOKS  = " Displaying all borrowed books... \n";
	
	
	//option 4 / option 5
	public static final String strPROMPT_ENTER_LOANID      = " Enter Loan ID     (enter X to go back to main menu): > ";
	
	
	//option 6
	public static final String strPROMPT_ENTER_BOOKID      = " Enter Book ID     (enter X to go back to main menu): > ";
	public static final String strPROMPT_ENTER_BOOKTITLE   = " Enter Book Title  (enter X to go back to main menu): > ";
	public static final String strPROMPT_ENTER_BOOKAUTHOR  = " Enter Book Author (enter X to go back to main menu): > ";
	
	
	//generic
	
	public static final String strPROCESSLOADING          = " Processing the request... ";
	public static final String strSELECTEDBOOKUNAVAILABLE = " Sorry, the selected book is not available.";
	
	
	//error prompts
	public static final String strINVALID_USERNAME      = " ERROR! Invalid user name.";
	
	public static final String strINVALID_MENU_CHOICE   = "\n Invalid choice. Please try again.";

	public static final String strERROR_BOOK_NOT_FOUND  = " Book not found. Please enter another one: \n";
	
	public static final String strERROR_BOOK_OUT        = " Selected book is currently out. \n";
	
	public static final String strINVALID_LOAN_ID       = " Invalid loan ID. \n";

	public static final String strINVALID_LOAN_ID_FOUND = " Existing loan ID. \n";

	public static final String strNORECORDFOUND         = "     No record found";

	public static final String strERROR_BOOK_EXIST      = " Invalid! Book ID already exists. \n";

	public static final String strERROR_INVALID_INPUT   = " Invalid input! \n";
	
	public static final String strERROR_NO_BOOKS_AVAILABLE = "   No Books Available.";
	
	public static final String strERROR_NULL_TITLE		= " Title cannot be null or empty.";
	
	public static final String strERROR_NULL_AUTHOR		= " Author cannot be null or empty.";
	
	public static final String strERROR_NULL_BOOK_ID    = " Book ID cannot be empty or null.";
	
	public static final String strERROR_BOOK_ID_MUST_BE_NUMERIC = " Book ID must be numeric.";
	
	public static final String strERROR_INVALID_BOOK_ID = "Book ID is invalid.";
	
	public static final String strERROR_MAIN_MENU		= " Going back to Main Menu. ";
	
}
