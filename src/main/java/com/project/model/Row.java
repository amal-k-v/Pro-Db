package com.project.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;


@Entity
@Table(name="row_details")
public class Row{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="row_id")
	Long id;
	@Column(name ="row_name")
	String rowName;
	
	@Column(name ="row_data")
	JSONObject rows;
	
	@Column(name ="row_key")
	Long rowKey;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public JSONObject getRows() {
		return rows;
	}
	public void setRows(JSONObject rows) {
		this.rows = rows;
	}
	public Long getRowKey() {
		return rowKey;
	}
	public void setRowKey(Long rowKey) {
		this.rowKey = rowKey;
	}
	
	
	
	
	
	
}