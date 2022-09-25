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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Component;
import com.cdac.entity.Feedback;
import com.cdac.entity.Order;
import com.cdac.entity.User;
import com.cdac.service.ComponentService;
import com.cdac.service.CustomerService;
import com.cdac.service.EmployeeService;
import com.cdac.service.FeedbackService;
import com.cdac.service.OrderService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ComponentService componentService;
	
	
	@ResponseBody
	@GetMapping("/customers")
	public List<User> getAllCustomers() {
	    return customerService.fetchAllCustomers();
	}
	
	@ResponseBody
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
	    return orderService.fetchAllOrders();
	}
	
	@ResponseBody
	@GetMapping("/feedbacks")
	public List<Feedback> getAllFeedbacks() {
	    return feedbackService.fetchAllFeedbacks();
	}
	
	/*
	 * @PostMapping("/add-employee") public String addEmployee(@RequestBody Employee
	 * employee) { employeeService.save(employee); return
	 * "Employee Added successfully"; }
	 */

	@ResponseBody
	@GetMapping("/employees")
	public List<User> getAllEmployees() {
	    return employeeService.fetchAllEmployees();
	}
	
	@PutMapping("/updateemployee/{id}")
	public String employeeUpdate(@PathVariable int id ,@RequestBody User user) {
		employeeService.updateById(id, user);
		return "emoloyee updated successfully";
	}
	
	@PutMapping("/updateorder/{id}")
	public String orderUpdate(@PathVariable int id, @RequestBody Order order) {
		orderService.updateById(id, order);
		return "order updated successfully";
	}
	
	@DeleteMapping("/deleteOrder/{id}")
	public String deleteOrder(@PathVariable int id) {
		orderService.delete(id);
		return "order deleted successfully";
	}
	
	@PostMapping("/addComponent")
	public String addOrder(@RequestBody Component component) {
		componentService.save(component);
		return "Component added successfully";
	}
	
	@ResponseBody
	@GetMapping("/components")
	public List<Component> getAllComponents(){
		return componentService.fetchAllComponents();
	}
	
	@PutMapping("/updateComponent/{id}")
	public String componentUpdate(@PathVariable int id, @RequestBody Component component) {
		componentService.updateById(id, component);
		return "Component updated successfully";
	}

	
	@DeleteMapping("/componentDelete/{id}")
	public String componentDelete(@PathVariable int id) {
		componentService.remove(id);
		return "success";
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public String employeeDelete(@PathVariable int id) {
		employeeService.remove(id);
		return "success";
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public String customerDelete(@PathVariable int id) {
		customerService.remove(id);
		return "success";
	}
}
