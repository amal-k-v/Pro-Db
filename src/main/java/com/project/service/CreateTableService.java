package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.CreateTable;
import com.project.model.User;
import com.project.repository.CreateTableRepo;

@Service
@Component
public class CreateTableService {
	
	@Autowired CreateTableRepo createTableRepo;
	
	  public void createATable( CreateTable  table) {
		  createTableRepo.save(table);
	  }
	  
	  public List<CreateTable> getAllTable()   
	  {  
	        List<CreateTable> tables = new ArrayList<CreateTable>();  
	        createTableRepo.findAll().forEach(table -> tables.add(table));  
	        return tables;  
	  } 
	  
	  public CreateTable  getTablesById(long id)   
	  {  
	        return createTableRepo.findById(id).get();  
	  }
	  
	  public void delete(Long tableid)   
	  {  
		  createTableRepo.deleteById(tableid);
	  }
	  

}
