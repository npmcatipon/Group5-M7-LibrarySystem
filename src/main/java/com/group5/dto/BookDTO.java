package com.group5.dto;

import com.group5.model.Book;

public class BookDTO {

	private Long id;
	private String title;
	private String author;
	private boolean isBorrowed;
	
	public BookDTO() {}
	
	public BookDTO(Long id, String title, String author, boolean isBorrowed) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isBorrowed = isBorrowed;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public boolean isBorrowed() {
		return isBorrowed;
	}
	
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	public BookDTO (Book bookEntity) {
		this.setId(bookEntity.getId());
		this.setTitle(bookEntity.getTitle());
		this.setAuthor(bookEntity.getAuthor());
		this.setBorrowed(bookEntity.isBorrowed());
	}
	
	public Book toEntity() {
		Book book = new Book();
		book.setId(this.getId());
		book.setTitle(this.getTitle());
		book.setAuthor(this.getAuthor());
		book.setIsBorrowed(this.isBorrowed());
		return book;
	}
	
	
}
