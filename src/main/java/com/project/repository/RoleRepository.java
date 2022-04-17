package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.Role;
import com.project.model.RoleName;


@Repository
@Component
public interface RoleRepository extends JpaRepository<Role,Long>{
	
	Optional<Role> findByName(RoleName roleName);

}
