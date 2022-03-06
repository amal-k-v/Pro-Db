package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.CreateTable;

@Repository
@Component
public interface CreateTableRepo extends JpaRepository<CreateTable,Long>{

}
