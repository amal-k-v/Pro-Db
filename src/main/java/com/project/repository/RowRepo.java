package com.project.repository;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.Row;

@Repository
@Component
public interface RowRepo extends JpaRepository<Row,Long>{
	
	
	       @Query(value = "select max(row_id) from row_details;", nativeQuery = true)
		   Long getMaxRowId();
	       
	       
	       @Query(value = "DELETE FROM row_details WHERE row_key IN(?1) RETURNING (row_id)", nativeQuery = true)
		   List<Long> deleteRowByKey( Long key);
	     
	       
	       @Query(value = "SELECT row_data FROM row_details WHERE row_key =?1", nativeQuery = true)
	       JSONObject getRowByKey( Long key);
	       
	       
	       Row findByrowKey(Long key);
	      
}