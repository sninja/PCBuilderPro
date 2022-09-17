package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Customer;
import com.cdac.exception.CustomerServiceException;
import com.cdac.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public String addCustomer(@RequestBody Customer customer) {
		try {
			customerService.save(customer);
			return "Registration successful";
		} catch(CustomerServiceException e){
			return e.getMessage();
		}
	}
	
	@ResponseBody
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
	    return customerService.fetchAllCustomers();
	}
 
}
