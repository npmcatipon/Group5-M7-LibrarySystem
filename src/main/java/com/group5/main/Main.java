package com.group5.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group5.controller.BookController;
import com.group5.service.BookService;
import com.group5.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

import static spark.Spark.*;


public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
		
		port(4567);
		
		BookService bookService = new BookService(em);
		BookController bookController = new BookController(bookService); 
		
		bookController.registerRoutes();
		
		logger.info("Server started at port {}", port());

	}
}
