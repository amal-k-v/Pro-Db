package com.project.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.model.CreateTable;
import com.project.model.User;
import com.project.security.CurrentUser;
import com.project.security.UserPrincipal;
import com.project.service.CreateTableService;
import com.project.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/table")
public class TableController {
	
	

	@Autowired CreateTableService createTableService;
	@Autowired UserService userService;
	
	
	
	@PostMapping("/create")  
	private Long createUser(@RequestBody CreateTable table,@CurrentUser UserPrincipal currentUser)   
	{     User user=userService.getUsersById(currentUser.getId());
	      table.setUser(user);
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
	@PutMapping("/update/{tableid}/{type}")  
	private Long update(@RequestBody CreateTable table,@PathVariable("tableid") Long tableid,@PathVariable("type") String type)   
	{  
		createTableService.updateById(table, tableid,type);
	     return table.getId();  
	} 

	@GetMapping("/row/{tableid}")  
	private  List<JSONObject> getRows(@PathVariable("tableid") Long tableid)   
	{  
		return createTableService.getRows(tableid);
	    
	};
	
	@GetMapping("/byuser")  
	private List<CreateTable> getTableByUser(@CurrentUser UserPrincipal currentUser)   
	{  
		User user=userService.getUsersById(currentUser.getId());
	   
		return createTableService.getAllTableByUser(user);
	}

}
