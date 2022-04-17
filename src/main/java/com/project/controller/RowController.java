package com.project.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Row;
import com.project.service.RowService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/row")
public class RowController {
	
	@Autowired RowService rowService;
	
	@DeleteMapping("/{key}")  
	private void deleteRow(@PathVariable("key") String key)   
	{  
		rowService.deleteRowByKey(key);
	}
	
	
	@GetMapping("/{key}")  
	private  JSONObject getRowsBykey(@PathVariable("key") Long key)   
	{  
		return rowService.getRowByKey(key);
	    
	};

	@GetMapping("/find/{key}")  
	private JSONObject  findRowsBykey(@PathVariable("key") Long key)   
	{  
		return rowService.findRowByKey(key);
	    
	};
	
	@PutMapping("/update/{key}")  
    private void updateRowByKey(@RequestBody JSONObject rowdata,@PathVariable("key")Long key) {
		
	   rowService.updateRowByKey(rowdata, key);
		
	}
}
