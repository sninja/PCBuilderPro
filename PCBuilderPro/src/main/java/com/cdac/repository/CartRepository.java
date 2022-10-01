package com.cdac.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.Cart;

public interface CartRepository extends CrudRepository<Cart,Integer> {

	
	boolean existsByName(String name);
	
	@Query("SELECT SUM(c.price) FROM Cart c")
	public float fetchBill();
}
