package com.group5.controller;

import com.group5.dto.ResponseDTO;
import com.group5.model.Book;
import com.group5.dto.BookDTO;
import com.group5.service.BookService;
import com.group5.util.JsonUtil;
import com.group5.util.ResponseStatus;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	public void registerRoutes() {
		
		get("/check-connection", (req,res) -> {
			res.type("application/json");
			
			Map<String, String> response = new HashMap<>();
			
			response.put("status", "Server is running");
			logger.info("Server is running on port {}", port());
			
			return JsonUtil.toJson(response);
		});
		
		// Display All Books
		get("/allbooks", (req,res) -> {
			
			res.type("application/json");
			ResponseDTO<List <BookDTO>> response = 
					new ResponseDTO<>(ResponseStatus.SUCCESS, "List of Available Books", this.bookService.getAllBooks());
			
			logger.info("Listing all books.");
			
			return JsonUtil.toJson(response);
		});
		
		// Display Available Books
		get("/availablebooks", (req, res) -> {
		
			res.type("application/json");
			ResponseDTO<List<BookDTO>> response = 
					new ResponseDTO<>(ResponseStatus.SUCCESS, "List of Available Books", this.bookService.getAvailableBooks());
		
			logger.info("Listing all available books.");
		
			return JsonUtil.toJson(response);
		});

		// Display All Borrowed Books
		get("/borrowedbooks", (req, res) -> {
		
			res.type("application/json");
			ResponseDTO<List<BookDTO>> response = 
					new ResponseDTO<>(ResponseStatus.SUCCESS,"List of borrow books.",this.bookService.getBorrowedBooks());
		
			logger.info("Listing all borrowed book.");
		
			return JsonUtil.toJson(response);
		});
		
		// Borrow Book
		put("/borrow/:id", (req, res) -> {
			
			res.type("application/json");
			
			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
		
			String id = req.params("id");
			Book book = bookService.findById(Long.valueOf(id));

			book = bookService.updateBookStatus(book, true);
			
			List<BookDTO> bookDTO = new ArrayList<>();
			bookDTO.add(new BookDTO(book));
			
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("Borrowed book id:" + id);
			logger.info("Book ID: {} has been borrowed.", id);
		
			response.setData(bookDTO);
		
			return JsonUtil.toJson(response);
		});
		
		// Return Book
		put("/return/:id", (req, res) -> {

			res.type("application/json");

			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();

			String id = req.params("id");
			Book book = bookService.findBorrowedBookById(Long.valueOf(id));

			book = bookService.updateBookStatus(book, false);

			List<BookDTO> bookDTO = new ArrayList<>();
			bookDTO.add(new BookDTO(book));

			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("Returned book id:" + id);
			logger.info("Book ID: {} has been returned to the Library.", id);

			response.setData(bookDTO);

			return JsonUtil.toJson(response);
		});
		
	}

}
