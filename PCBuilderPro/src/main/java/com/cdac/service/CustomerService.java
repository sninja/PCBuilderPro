package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.entity.Order;
import com.cdac.entity.User;
import com.cdac.exception.CustomerServiceException;
import com.cdac.repository.OrderRepository;
import com.cdac.repository.UserRepository;

@Service
@Transactional
public class CustomerService {
	

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * public void save(User user) {
	 * if(customerRepository.existsByEmail(customer.getEmail())) throw new
	 * CustomerServiceException("User already registered..!"); else
	 * customerRepository.save(customer); }
	 */
	
	public List<User> fetchAllCustomers(){
		return (List<User>)userRepository.findByRole(3);
	}
	
	public List<Order> fetchOrder(int id){
		return orderRepository.findBycustid(id);
	}
	
	public void remove(int id) {
		userRepository.deleteById(id);
	}
	
	/*
	 * public Customer login(String email, String password){ if
	 * (customerRepository.existsByEmailandPassword(email, password)) return
	 * customerRepository.fetch(email); else throw new
	 * CustomerServiceException("Email/password incorrect"); }
	 */
}
