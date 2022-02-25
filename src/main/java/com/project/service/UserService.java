package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.repository.UserRepo;

@Service
@Component
public class UserService {
	
	@Autowired UserRepo userrepo;
	
	
	
	  public void addUser( User user ) {
		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    String encodedPassword = passwordEncoder.encode(user.getPassword());
		    user.setPassword(encodedPassword);
	        userrepo.save(user);
	  }
	  
	  public List<User> getAllUsers()   
	  {  
	        List<User> users = new ArrayList<User>();  
	        userrepo.findAll().forEach(user -> users.add(user));  
	        return users;  
	  } 
	  
	  
	  public User getUsersById(long id)   
	  {  
	        return userrepo.findById(id).get();  
	  }
	  
	  public void delete(Long userid)   
	  {  
	        userrepo.deleteById(userid);  
	  } 
	  
	  public void updateById(Long userid)   
	  {  
	        userrepo.save(getUsersById(userid));
	    
	  } 
 

}
