package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdac.entity.Customer;
import com.cdac.service.CustomerService;

public class EmployeeController {
	
	@Autowired
	private CustomerService customerService;
	
	@ResponseBody
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
	    return customerService.fetchAllCustomers();
	}
}
