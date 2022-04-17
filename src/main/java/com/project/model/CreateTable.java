package com.project.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@Column(name ="created_date")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss",timezone="Asia/Kolkata")
	Date createdDate;
	
	@Column(name ="modified_date")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss",timezone="Asia/Kolkata")
	Date modifiedDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="table_column_id")  
	List<Columns> columns;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="table_row_id")  
	List<Row> rows;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="table_user_id")  
	private User user ;
	
	



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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	
	
	
	
	
}
