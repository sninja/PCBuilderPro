package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Component;
import com.cdac.entity.Customer;

public interface ComponentRepository extends CrudRepository<Component,Integer>{
	
	//@Query("select a.name from Component c JOIN c.components a where b.id = ?1")
	@Query(
			  value = "SELECT component.*\r\n"
			  		+ "FROM component\r\n"
			  		+ "INNER JOIN order_component\r\n"
			  		+ "ON component.comp_id = order_component.comp_id\r\n"
			  		+ "WHERE order_component.order_id = :id", 
			  nativeQuery = true)
	public List<Component> fetchComponentofOrder(@Param("id") int id);
}



