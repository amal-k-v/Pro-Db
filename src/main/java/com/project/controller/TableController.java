package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.CreateTable;
import com.project.model.User;
import com.project.service.CreateTableService;
import com.project.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/table")
public class TableController {
	
	

	@Autowired CreateTableService createTableService;
	
	
	
	@PostMapping("/create")  
	private Long createUser(@RequestBody CreateTable table)   
	{  
		  createTableService.createATable(table);
		  return table.getId();
	}
	
	@GetMapping("/list")  
	private List<CreateTable > getAllTable()   
	{  
		return  createTableService.getAllTable();
	} 
	
	@GetMapping("/{tableId}")  
	private CreateTable getUser(@PathVariable("tableId")Long tableid)   
	{  
	  return createTableService.getTablesById(tableid);
	}  
	
	@DeleteMapping("/{tableid}")  
	private void deleteTable(@PathVariable("tableid") Long tableid)   
	{  
		createTableService.delete(tableid);
	}



}