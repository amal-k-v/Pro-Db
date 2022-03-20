package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.AuthenticationBean;
import com.project.model.User;
import com.project.service.UserService;

@CrossOrigin(origins={ "http://localhost:3000"})
@RestController
public class BasicAuthenticationController {
	@Autowired UserService userservice;

	  
	
	@GetMapping(path = "/basicauth")
    public AuthenticationBean authenticate() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
		
		
        return new AuthenticationBean("You are authenticated");
    }  
	
	@PostMapping(path="/userlogin")
	public AuthenticationBean Login(@RequestBody User user) {
		
         List<User> users=userservice.getAllUsers();
		
		for(User userdata: users) {
			
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if(userdata.getName().equals(user.getName())&&passwordEncoder.matches(user.getPassword(),userdata.getPassword())) {
				    
				return new AuthenticationBean("You are authenticated");   
			}
			 
		}
		return new AuthenticationBean("You are not authenticated");
	}
	
	
	

}
