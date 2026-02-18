package com.group5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table ( name = "users", schema = "m6")
public class User {
		
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column ( nullable = false,
			  columnDefinition = "VARCHAR(255)")
	private String name;
	
	public User() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
