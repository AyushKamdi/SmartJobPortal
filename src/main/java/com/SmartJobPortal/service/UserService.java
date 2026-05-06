package com.SmartJobPortal.service;

import com.SmartJobPortal.model.User;

public interface UserService {
	
	
    User registerUser(User user);
    
 
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
