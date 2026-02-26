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
					new ResponseDTO<>(ResponseStatus.SUCCESS,
                                      "List of All Books",
                                      this.bookService.getAllBooks());
			
			logger.info("Listing all books.");
			
			return JsonUtil.toJson(response);
		});
		
		// Display Available Books
		get("/availablebooks", (req, res) -> {
		
			res.type("application/json");
			ResponseDTO<List<BookDTO>> response = 
					new ResponseDTO<>(ResponseStatus.SUCCESS,
                                      "List of Available Books",
                                      this.bookService.getAvailableBooks());
		
			logger.info("Listing all available books.");
		
			return JsonUtil.toJson(response);
		});

		// Display All Borrowed Books
		get("/borrowedbooks", (req, res) -> {
		
			res.type("application/json");
			ResponseDTO<List<BookDTO>> response = 
					new ResponseDTO<>(ResponseStatus.SUCCESS,
                                      "List of Borrowed Books.",
                                      this.bookService.getBorrowedBooks());
		
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
			response.setData(bookDTO);

			logger.info("Book ID: {} has been borrowed.", id);

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
			response.setData(bookDTO);

			logger.info("Book ID: {} has been returned to the Library.", id);

			return JsonUtil.toJson(response);
		});
		
		// Add Book
		post("/addbook", (req, res) -> {

			res.type("application/json");

			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			
			if (req.body() == null || req.body().isBlank()) {
				res.status(422);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Request body is required.");
				return JsonUtil.toJson(response);
			}
			
			BookDTO bookDTO = JsonUtil.fromJson(req.body(), BookDTO.class);
			
			if (bookDTO.getTitle() == null || 
					bookDTO.getTitle().isBlank()) {
				res.status(422);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Title cannot be empty or null.");
				response.setData(null);
				return JsonUtil.toJson(response);
			}
			
			if (bookDTO.getAuthor()== null ||
					bookDTO.getAuthor().isBlank()) {
				res.status(422);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Author cannot be empty or null.");
				return JsonUtil.toJson(response);				
			}
					
			
			Book newBook = bookService.addBook(bookDTO.toEntity());

			res.status(201);

			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("Book Added");
			response.setData(new ArrayList<BookDTO>(List.of(new BookDTO(newBook))));

			logger.info("Added BookID: {}, Book Title: {}, Book Author: {} to the Library.", newBook.getId(), newBook.getTitle(), newBook.getAuthor());

			return JsonUtil.toJson(response);
		});
		
		// Remove Book
		put("/removebook/:id", (req, res) -> {

			res.type("application/json");

			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			
			String id = req.params("id");
			
			if (id == null || id.isBlank()) {
				res.status(400);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Invalid ID number");
				return JsonUtil.toJson(response);
			}
			
			Book book = bookService.findById(Long.valueOf(id));
			
			if (book == null) {
				res.status(404);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Book ID not found");
				return JsonUtil.toJson(response);
			}
			
			bookService.removeBook(Long.valueOf(id));
			
			res.status(200);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("Book ID " + id + " has been removed from the Library");
			response.setData(List.of(new BookDTO(book)));

			return JsonUtil.toJson(response);
		});
		
		// Update Book
		post("/updatebook", (req, res) -> {

			res.type("application/json");

			ResponseDTO<List<BookDTO>> response = new ResponseDTO<>();
			
			if (req.body() == null ||
					req.body().isBlank()) {
				res.status(400);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Request body is required.");
				return JsonUtil.toJson(response);
			}
			
			BookDTO bookDTO = JsonUtil.fromJson(req.body(), BookDTO.class);
			
			if (bookDTO.getId() == null) {
				res.status(409);
				response.setStatus(ResponseStatus.ERROR);
				response.setMessage("Book ID not found.");
				return JsonUtil.toJson(response);
			}
			
			bookDTO = new BookDTO(bookService.addBook(bookDTO.toEntity()));
			
			res.status(200);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("Book ID " + bookDTO.getId() + " has been updated.");
			response.setData(List.of(bookDTO));

			return JsonUtil.toJson(response);
		});
		
	}

}
