package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.User;

@Repository
@Component
public interface UserRepo extends JpaRepository<User,Long>{

}
