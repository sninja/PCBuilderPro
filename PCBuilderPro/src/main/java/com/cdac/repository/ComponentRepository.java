package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Component;


public interface ComponentRepository extends CrudRepository<Component,Integer>{
	
	
	//Component findById(int id);
	//@Query("select a.name from Component c JOIN c.components a where b.id = ?1")
	@Query(
			  value = "SELECT component.*\r\n"
			  		+ "FROM component\r\n"
			  		+ "INNER JOIN order_details\r\n"
			  		+ "ON component.comp_id = order_details.comp_id\r\n"
			  		+ "WHERE order_details.order_id = :id", 
			  nativeQuery = true)
	public List<Component> fetchComponentofOrder(@Param("id") int id);
}



