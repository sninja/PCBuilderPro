package com.cdac.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

}
