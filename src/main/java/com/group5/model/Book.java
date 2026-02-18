package com.group5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book", schema = "m6")
public class Book {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column ( name = "title",
			  nullable = false,
			  columnDefinition = "VARCHAR(255)")
	private String title;
	
	@Column ( name = "author",
			  nullable = false,
			  columnDefinition =  "VARCHAR(255)")
	private String author;
	
	@Column ( name = "is_borrowed",
			  nullable = false,
			  columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean isBorrowed;

	public Book () {
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
	public void setIsBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
}
