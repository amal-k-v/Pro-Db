package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.Columns;


@Repository
@Component
public interface ColumnsRepo extends JpaRepository<Columns,Long>{

}