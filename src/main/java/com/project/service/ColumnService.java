package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.Columns;
import com.project.repository.ColumnsRepo;

@Service
@Component
public class ColumnService {
	
	@Autowired ColumnsRepo columnrepo;
	
	 public void createColumn(Columns column) {
		  columnrepo.save(column);
	  }
	  
	  public List<Columns> getAllColumns()   
	  {  
	        List<Columns>columns = new ArrayList<Columns>();  
	        columnrepo.findAll().forEach(column ->columns.add(column));  
	        return columns;  
	  } 
	  
     public List<Columns> getColumnByTableId(Long id){
    	 
    	return columnrepo.getColumnByTableId(id);
     }
     
     public List<Columns> getColumnByTableIdForTable(Long id){
    	 
     	return columnrepo.getColumnByTableIdForTable(id);
      }
}
