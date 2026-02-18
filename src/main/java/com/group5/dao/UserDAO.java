package com.group5.dao;

import com.group5.model.User;

public interface UserDAO {
	
	User isUserExisting(String id, String username);
	
}
