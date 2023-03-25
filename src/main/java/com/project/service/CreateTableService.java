package com.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.model.Columns;
import com.project.model.CreateTable;
import com.project.model.Row;
import com.project.model.User;
import com.project.repository.CreateTableRepo;
import com.project.repository.RowRepo;

@Service
@Component
public class CreateTableService {
	
	@Autowired CreateTableRepo createTableRepo;
	
	@Autowired RowRepo rowrepo;
	
	  public void createATable( CreateTable  table) {
		  //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		  Date date = new Date();  
		  table.setCreatedDate(date);
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
		  
		  CreateTable table=getTablesById(tableid);
		  table.setUser(null);
		  //createTableRepo.save(table);
		  createTableRepo.deleteById(tableid);
	  }
	  
	  public void updateById(CreateTable createdtable,Long tableid,String type)   
	  {   
		  CreateTable table= getTablesById(tableid);
		  if(type==null||type.isEmpty()||type.equals("all")) {
		  table.setName(createdtable.getName());
		  table.setDescription(createdtable.getDescription());
		  table.setColumns(createdtable.getColumns());
		  }
		  else if (type.equals("name")) {
			  table.setName(createdtable.getName());
			}
		  else if (type.equals("des")) {
			  table.setDescription(createdtable.getDescription());
			}
		  else if (type.equals("col")) {
//			  List<Columns>col=createdtable.getColumns();
//			  for(Columns columns:col) {
//				  String field=columns.getField();
//				  Integer len=field.length();
//				  columns.setWidth(len*25);
//			  }
			  table.setColumns(createdtable.getColumns());
			}
		  else if (type.equals("row")) {
			 
			  List<Columns>col=createdtable.getColumns();
			  JSONObject obj=new JSONObject(); 
		
			  Long rowKey=null;
			 for(Columns columns:col) {
				 System.out.println(columns);
				 
				 System.out.println("Rows-->"+columns.getRows());
				
				 System.out.println("//---------------------//");
				 System.out.println("Json-->");
				 List<Row>row=columns.getRows();
				 List<Row>rowData=  table.getRows();
				 int id=(rowData.size()+1);
				 Long rowid=rowrepo.getMaxRowId()+1;
				 rowKey=Long.parseLong(table.getId().toString()+rowid.toString());
						// Integer.toString(id));
					
				 obj.put("id",rowKey); 
				 obj.put("color","default"); 
				
				 for(Row rows:row) {
					 
					 obj.put(columns.getField(),rows.getRowName()); 
				 }
				 
				 
			 }
			  System.out.print("obj-->"+obj);
			List<Row>rowData=  table.getRows();
			Row addRow=new Row();
		    addRow.setRowKey(rowKey);
			addRow.setRows(obj);
			rowData.add(addRow);
			
			
			table.setRows(rowData);
			
			}
		  createTableRepo.save(table);
	  }
	  
	  
	  public List<JSONObject> getRows(Long tableid)   
	  {  
		  CreateTable table= getTablesById(tableid);
		  List<Row>rowData=  table.getRows(); 
	      List<JSONObject>rowArr=new ArrayList<JSONObject>();
	      
	      for(Row row:rowData) {
	    	  rowArr.add(row.getRows());
	      }
		return rowArr;
	      
	      
	  } 
	  
	  
	  public List<CreateTable> getAllTableByUser(User user)   
	  {  
	       // List<CreateTable> tables = new ArrayList<CreateTable>();  
	      //  createTableRepo.findByUser(user).forEach(table -> tables.add(table));  
	        return createTableRepo.findByUser(user); 
	  } 

}
