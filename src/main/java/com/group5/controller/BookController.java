package com.group5.controller;

import com.group5.service.BookService;
import com.group5.util.JsonUtil;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

public class BookController {
	
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	public void registerRoutes() {
		
		get("/check-connection", (req,res) -> {
			res.type("application/json");
			
			Map<String, String> response = new HashMap<>();
			
			response.put("status", "Server is running");
			
			return JsonUtil.toJson(response);
		});
		
	}

}
