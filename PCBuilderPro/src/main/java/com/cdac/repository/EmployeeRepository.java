package com.cdac.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.User;


public interface EmployeeRepository extends CrudRepository<User,Integer> {

}
