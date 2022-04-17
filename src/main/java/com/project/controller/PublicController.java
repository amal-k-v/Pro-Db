package com.project.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.RowService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("public")

public class PublicController {
	
	@Autowired RowService rowService;
	
	@GetMapping("row/find/{key}")  
	private JSONObject  findRowsBykey(@PathVariable("key") Long key)   
	{  
		return rowService.findRowByKey(key);
	    
	};

}
