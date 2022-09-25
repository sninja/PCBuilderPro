package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Component;
import com.cdac.entity.Feedback;
import com.cdac.entity.Order;
import com.cdac.exception.CustomerServiceException;
import com.cdac.service.ComponentService;
import com.cdac.service.CustomerService;
import com.cdac.service.FeedbackService;
import com.cdac.service.OrderService;

@RestController
@CrossOrigin("http://localhost:3000/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ComponentService componentService;
	

	@Autowired
	private FeedbackService feedbackService;
	
	/*
	 * @PostMapping("/register") public String addCustomer(@RequestBody Customer
	 * customer) { try { customerService.save(customer); return
	 * "Registration successful"; } catch(CustomerServiceException e){ return
	 * e.getMessage(); } }
	 */
	
	@ResponseBody
	@GetMapping("/customerOrders/{id}")
	public List<Order> getAllOrders(@PathVariable int id) {
	    return customerService.fetchOrder(id);
	}
	
	@ResponseBody
	@GetMapping("/userComponents")
	public List<Component> getAllComponents(){
		return componentService.fetchAllComponents();
	}
	
	@PostMapping("/addOrder")
	public String addOrder(@RequestBody Order order) {
		orderService.save(order);
		return "order Added Successfully";
	}
	
	/*
	 * @PutMapping("/update-order") public String updateOrder(@RequestBody Order
	 * order) { orderService.save(order); return "Order updated successfully"; }
	 */
	
	
	
	@PostMapping("/addFeedback")
	public String addFeedback(@RequestBody Feedback feedback) {
		feedbackService.save(feedback);
		return "feedback Added Successfully";
	}
	
	@ResponseBody
	@GetMapping("/orderComponents")
	public List<Component> fetchOrderComponents(@RequestParam("id") int id) {
	    return componentService.fetchOrderComponents(id);
	}
}
