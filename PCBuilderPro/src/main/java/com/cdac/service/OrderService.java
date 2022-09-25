package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Order;

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
	
	public void updateById(int id, Order order) {
		Optional<Order> order1 = orderRepository.findById(id);
		if(order1.isPresent()) {
			Order dborder = order1.get();
			dborder.setOrderDate(order.getOrderDate());
			dborder.setDeliveredDate(order.getDeliveredDate());
			dborder.setStatus(order.getStatus());
			dborder.setTrasactionId(order.getTrasactionId());
			orderRepository.save(dborder);
		}
		
	}
	
	public void update(Order order) {
		orderRepository.save(order);
	}
	
	public void delete(int id) {
		orderRepository.deleteById(id);
	}

}
