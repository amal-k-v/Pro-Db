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

@Entity
@Table(name="columns_details")
public class Columns {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="column_id")
	Long id;
	@Column(name ="field")
	String field;
	@Column(name ="header_name")
    String  headerName;
	@Column(name ="type")
    String type;
	@Column(name ="width")
    Integer width;
	@Column(name ="editable")
    Boolean editable;
	
	@Column(name ="header_class")
	String headerClassName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="column_row_id")  
	List<Row> rows;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	public String getHeaderClassName() {
		return headerClassName;
	}
	public void setHeaderClassName(String headerClassName) {
		this.headerClassName = headerClassName;
	}
	@Override
	public String toString() {
		return "Columns [id=" + id + ", field=" + field + ", headerName=" + headerName + ", type=" + type + ", width="
				+ width + ", editable=" + editable + ", rows=" + rows + "]";
	}
	
	
	

}
