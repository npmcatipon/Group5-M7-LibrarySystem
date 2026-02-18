package com.group5.service.impl;

import com.group5.model.User;
import com.group5.repository.impl.UserRepositoryImpl;
import com.group5.service.UserService;

import jakarta.persistence.EntityManager;

public class UserServiceImpl implements UserService {
	
	private final EntityManager em;
	
	private final UserRepositoryImpl userRepository;
	
	public UserServiceImpl(EntityManager em) {
		this.em = em;
		this.userRepository = new UserRepositoryImpl(em);
	}
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}
}
