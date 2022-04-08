package com.project.controller;

import org.springframework.security.authentication.AuthenticationManager;

import com.project.exception.AppException;
import com.project.model.Role;
import com.project.model.RoleName;
import com.project.model.User;
import com.project.payload.ApiResponse;
import com.project.payload.JwtAuthenticationResponse;
import com.project.payload.LoginRequest;
import com.project.payload.SignUpRequest;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepo;
import com.project.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	 @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepo userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    JwtTokenProvider tokenProvider;

	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    	System.out.println("//name and Pw==>"+loginRequest.getUsernameOrEmail()+"//"+loginRequest.getPassword());
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsernameOrEmail(),
	                        loginRequest.getPassword()
	                )
	        );
	        System.out.println("auth//-->");
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }
         
	    
	    
	    
	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	        if(userRepository.existsByUserName(signUpRequest.getUsername())) {
	            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	        // Creating user's account
	        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
	                signUpRequest.getEmail(), signUpRequest.getPassword());
            System.out.println("//name==>"+signUpRequest.getName());
	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new AppException("User Role not set."));

	        user.setRoles(Collections.singleton(userRole));
	        System.out.println("//Role==>"+userRole.getName());
	        User result = userRepository.save(user);

			
		  URI location = ServletUriComponentsBuilder
		 .fromCurrentContextPath().path("/users/{username}")
			  .buildAndExpand(result.getUserName()).toUri();
			 

	        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	      //  return new ResponseEntity(new ApiResponse(false, "User registered successfully"),
                 //   HttpStatus.CREATED);
	    }

}
