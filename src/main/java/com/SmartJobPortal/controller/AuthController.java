package com.SmartJobPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SmartJobPortal.model.User;
import com.SmartJobPortal.service.UserService;

@Controller
public class AuthController {

	@Autowired
    private UserService userService;

  
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User user) {
        
    	user.setUsername(user.getEmail());
    	
        
        userService.registerUser(user);
        
        
        return "redirect:/dashboard.html"; 
    }
	
}
