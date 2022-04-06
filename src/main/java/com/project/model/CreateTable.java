package com.project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Table(name="create_table_details")
public class CreateTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="table_id")
	Long id;
	@Column(name ="table_name")
	String name;
	@Column(name ="table_description")
	String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="table_column_id")  
	List<Columns> columns;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="table_row_id")  
	List<Row> rows;
	
	
	



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Columns> getColumns() {
		return columns;
	}
	public void setColumns(List<Columns> columns) {
		this.columns = columns;
	}
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	
	
	
	
	
}
