package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Customer;
import com.cdac.entity.Order;
import com.cdac.exception.CustomerServiceException;
import com.cdac.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void save(Order order) {
			orderRepository.save(order);
	}
	
	public List<Order> fetchAllOrders(){
		return (List<Order>) orderRepository.findAll();
	}
	
	public void update(Order order) {
		orderRepository.save(order);
}
}
