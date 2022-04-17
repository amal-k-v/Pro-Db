package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Columns;
import com.project.model.CreateTable;
import com.project.service.ColumnService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/column")
public class ColumnsController {
	
	
	@Autowired ColumnService columnService;
	
	@PostMapping("/create")  
	private Long createColumn(@RequestBody Columns column)   
	{  
		  columnService.createColumn(column);
		  return column.getId();
	}
	
	@GetMapping("/list")  
	private List<Columns> getAllColumns()   
	{  
		return  columnService.getAllColumns();
	} 
	
	@GetMapping("/{id}")  
	private List<Columns> getColumnsByTableId(@PathVariable("id") Long id)   
	{  
		return  columnService.getColumnByTableId(id);
	} 
	
	@GetMapping("/table/{id}")  
	private List<Columns> getColumnsByTableIdForTable(@PathVariable("id") Long id)   
	{  
		return  columnService.getColumnByTableIdForTable(id);
	} 
	

}
