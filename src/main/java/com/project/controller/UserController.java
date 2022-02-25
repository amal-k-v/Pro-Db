package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.User;
import com.project.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
	
	
	
	@Autowired UserService userservice;
	
	
	@GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "Amal") String name) {
		 return String.format("Hello %s!", name);
	}	
	
	@PostMapping("/create")  
	private Long createUser(@RequestBody User user)   
	{  
		 userservice.addUser(user);
		 return user.getId();
	}
	
	@GetMapping("/list")  
	private List<User> getAllUsers()   
	{  
		return userservice.getAllUsers();  
	} 

	@GetMapping("/{userid}")  
	private User getUser(@PathVariable("userid")Long userid)   
	{  
	  return userservice.getUsersById(userid);
	}  
	
	@DeleteMapping("/{userid}")  
	private void deleteUser(@PathVariable("userid") Long userid)   
	{  
	   userservice.delete(userid);
	}
	
	@PostMapping("/{userid}")  
	private void upadteUserById( @PathVariable("userid") Long userid)   
	{  
		 userservice.updateById(userid);
		 
	}
}
