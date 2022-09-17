package com.cdac.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.Component;
import com.cdac.entity.Customer;

public interface ComponentRepository extends CrudRepository<Component,Integer>{
	
}
