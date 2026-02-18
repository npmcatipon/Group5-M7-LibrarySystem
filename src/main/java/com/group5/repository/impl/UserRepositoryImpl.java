package com.group5.repository.impl;

import java.util.List;

import com.group5.model.User;
import com.group5.repository.Repository;

import jakarta.persistence.EntityManager;

public class UserRepositoryImpl implements Repository<User, Long>{
	
	private final EntityManager em;

	public UserRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public User save(User user) {
		
		if (user.getId() != null) {
			
			em.persist(user);
			
		} else {
			
			em.merge(user);
		}
		
		return user;
	}

	@Override
	public void delete(User user) {
		
		em.remove(em.contains(user) ? user : em.merge(user));
		
	}

	@Override
	public void deleteById(Long id) {
		
		User user = findById(id);
		
		if (user != null) {
			delete(user);
		}
	}

	@Override
	public User findById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		
		return em.createQuery("SELECT u FROM user u", User.class).getResultList();
		
	}

}
