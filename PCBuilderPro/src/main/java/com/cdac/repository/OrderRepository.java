package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Order;

public interface OrderRepository extends CrudRepository<Order,Integer>{
	
	@Query(value = "SELECT `order`.* FROM `order` WHERE cust_id = :id", nativeQuery = true)
	public List<Order> findBycustid(@Param("id") int id);
}
