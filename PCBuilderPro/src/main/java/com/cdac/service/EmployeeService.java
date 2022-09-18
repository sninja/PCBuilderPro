package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Employee;
import com.cdac.entity.Order;
import com.cdac.repository.EmployeeRepository;
import com.cdac.repository.OrderRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void save(Employee employee) {
			employeeRepository.save(employee);
	}
	
	public List<Employee> fetchAllEmployees(){
		return (List<Employee>) employeeRepository.findAll();
	}
	
	public void update(Employee employee) {
		employeeRepository.save(employee);
}
}
