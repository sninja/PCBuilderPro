package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Feedback;
import com.cdac.entity.Order;
import com.cdac.repository.FeedbackRepository;
import com.cdac.repository.OrderRepository;

@Service
@Transactional
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void save(int id, Feedback feedback) {
		Optional<Order> order = orderRepository.findById(id);
		feedback.setOrder(order.get());
		feedbackRepository.save(feedback);
	}
	
	public List<Feedback> fetchAllFeedbacks(){
		return (List<Feedback>) feedbackRepository.findAll();
	}
}
