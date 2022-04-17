package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.Row;
import com.project.repository.RowRepo;



@Service
@Component
public class RowService {
	
	@Autowired RowRepo rowrepo;
	
	public void deleteRowByKey(String key) {
		System.out.println("key-->"+key);
		List<Long> list = new ArrayList<Long>();
		for (String s : key.split(","))
		    list.add(Long.parseLong(s));
		for(Long rowKey:list) {
		  rowrepo.deleteRowByKey(rowKey);
		  System.out.println("//deleted Row //-->"+rowKey);
		}
		
		
		 
	 }
	
	public JSONObject getRowByKey(Long key) {
	
		JSONObject row=rowrepo.getRowByKey(key);
		
		return row;
		 
	 }
   
	public JSONObject  findRowByKey(Long key) {
		
		return rowrepo.findByrowKey(key).getRows();
		
		
		 
	 }
	
	public void updateRowByKey(JSONObject rowdata,Long key) {
		
		rowrepo.UpdateRowByKey(rowdata, key);
		
	}

	
}
