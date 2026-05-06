package com.SmartJobPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SmartJobPortal.model.User;
import com.SmartJobPortal.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        // 1. Hash the password before saving! Never save plain text passwords.
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 2. Set a default role if none is provided.
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER"); 
        }

        // 3. Save the user to the database using the repository.
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
