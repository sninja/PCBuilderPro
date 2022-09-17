package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Customer;
import com.cdac.exception.CustomerServiceException;
import com.cdac.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void save(Customer customer) {
		if(customerRepository.existsByEmail(customer.getEmail()))
			throw new CustomerServiceException("User already registered..!");
		else
			customerRepository.save(customer);
	}
	
	public List<Customer> fetchAllCustomers(){
		return (List<Customer>) customerRepository.findAll();
	}
	
	/*
	 * public Customer login(String email, String password){ if
	 * (customerRepository.existsByEmailandPassword(email, password)) return
	 * customerRepository.fetch(email); else throw new
	 * CustomerServiceException("Email/password incorrect"); }
	 */
}
