package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Feedback;
import com.cdac.repository.FeedbackRepository;

@Service
@Transactional
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	
	public List<Feedback> fetchAllFeedbacks(){
		return (List<Feedback>) feedbackRepository.findAll();
	}
}
