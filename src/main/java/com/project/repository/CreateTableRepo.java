package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.CreateTable;
import com.project.model.Role;
import com.project.model.RoleName;
import com.project.model.Row;
import com.project.model.User;

@Repository
@Component
public interface CreateTableRepo extends JpaRepository<CreateTable,Long>{
	
	
	List<CreateTable> findByUser(User user);
	
	   @Query(value = "UPDATE create_table_details SET table_user_id=(?1) WHERE table_id IN(?2) RETURNING (table_id)", nativeQuery = true)
	   List<Long> UpdateUserInTableById(Long val, Long key);
	
	

}
