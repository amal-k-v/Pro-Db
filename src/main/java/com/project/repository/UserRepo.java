package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.User;

@Repository
@Component
public interface UserRepo extends JpaRepository<User,Long>{
	
	Optional<User>findByUserName(String username);
	
	Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

   

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

}
