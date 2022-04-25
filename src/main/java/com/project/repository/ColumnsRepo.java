package com.project.repository;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.model.Columns;
import com.project.model.Row;


@Repository
@Component
public interface ColumnsRepo extends JpaRepository<Columns,Long>{
	
	 
	  @Query(value = "SELECT * FROM columns_details WHERE table_column_id=?1", nativeQuery = true)
	  List<Columns> getColumnByTableId( Long id);
	  
	  @Query(value = "SELECT * FROM columns_details WHERE table_column_id=?1 AND type IN('email')", nativeQuery = true)
	  List<Columns> getColumnByTableIdAndType( Long id);
	  
	  
	  @Query(value = "SELECT * FROM columns_details WHERE table_column_id=?1 and type NOT IN('note','heading')", nativeQuery = true)
	  List<Columns> getColumnByTableIdForTable( Long id);
	  
}