package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
	
	public boolean existsByEmail(String email);
	
	//public List<Customer> findByEmailandPassword(String email, String password);
}
