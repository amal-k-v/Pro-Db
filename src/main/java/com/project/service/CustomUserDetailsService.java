package com.project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.repository.UserRepo;
import com.project.security.UserPrincipal;

@Service
@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired UserRepo userRepo;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByUserName(username)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with username : " + username)
        );
		UserDetails userDetails=UserPrincipal.create(user);
		System.out.println("load User & pw==>  "+userDetails.getPassword()+"   pw==/"+userDetails.getPassword());
		return userDetails;
	}
	
	
	// This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }

}
