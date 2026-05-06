package com.SmartJobPortal.service;

import com.SmartJobPortal.model.User;

public interface UserService {
	
	// This method will handle saving a new user to the database
    User registerUser(User user);
    
    // We'll need a way to check if an email or username is already taken
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
