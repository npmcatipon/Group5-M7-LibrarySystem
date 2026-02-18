package com.group5.controller;

import com.group5.dto.ResponseDTO;
import com.group5.dto.BookDTO;
import com.group5.service.BookService;
import com.group5.util.JsonUtil;
import com.group5.util.ResponseStatus;

import static spark.Spark.*;

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
		
		get("/allbooks", (req,res) -> {
			
			res.type("application/json");
			ResponseDTO<List <BookDTO>> response = new ResponseDTO<>();
			
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("Retrieving list of all books.");
			logger.info("Connecting to database to list all books.");
			
			response.setData(this.bookService.getAllBooks());
			
			return JsonUtil.toJson(response);
		});
		
	}

}
