package com.cdac.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.cdac.entity.Bill;
import com.cdac.entity.Cart;
import com.cdac.entity.Component;
import com.cdac.entity.Feedback;
import com.cdac.entity.Order;
import com.cdac.entity.User;
import com.cdac.exception.CustomerServiceException;
import com.cdac.repository.CartRepository;
import com.cdac.repository.UserRepository;
import com.cdac.service.ComponentService;
import com.cdac.service.CustomerService;
import com.cdac.service.FeedbackService;
import com.cdac.service.OrderService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ComponentService componentService;
	

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	List<Component> temp = new ArrayList();
	
	/*
	 * @PostMapping("/register") public String addCustomer(@RequestBody Customer
	 * customer) { try { customerService.save(customer); return
	 * "Registration successful"; } catch(CustomerServiceException e){ return
	 * e.getMessage(); } }
	 */
	
	@ResponseBody
	@GetMapping("/customerOrders/{email}")
	public List<Order> getAllOrders(@PathVariable String email) {
		User user = userRepository.findByEmail(email);
		//System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDD ==== " + user.getId());
		return customerService.fetchOrder(user.getId());
	}
	
	@ResponseBody
	@GetMapping("/userComponents")
	public List<Component> getAllComponents(){
		return componentService.fetchAllComponents();
	}
	
	//add to cart table
	@PostMapping("/addCart/{id}")
	public String addtoCart(@PathVariable int id) {
		 Component component = componentService.fetchbyId(id);
		 Cart cart = new Cart();
		 cart.setName(component.getName());
		 cart.setPrice(component.getPrice());
		 cartRepository.save(cart);
		 
		 temp.add(component);  
		return " Added to cart Successfully";
	}
	
	
	@PostMapping("/addOrder/{email}")
	public String addOrder(@PathVariable String email) {
		
		/* Generating random transaction ID */
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	//========================================	
		User user = userRepository.findByEmail(email);
		List<Cart> cart = (List<Cart>) cartRepository.findAll();
		float bill = cartRepository.fetchBill();
		//creating bill
		Bill billob = new Bill();
		billob.setStatus("Paid");
		billob.setAmount(bill);
		
		//creating order
		Order order = new Order();
		order.setStatus("Processing");
		order.setTrasactionId(generatedString);
		order.setBill(billob);
		order.setUser(user);
		order.setComponents(temp);
		componentService.stockUpdate(temp);
		billob.setOrder(order);
		orderService.save(order);
		//temp.clear();
		cartRepository.deleteAll();
		return "order Added Successfully";
	}
	
	@GetMapping("/getCart")
	public List<Cart> getCartItems(){
		return (List<Cart>) cartRepository.findAll();
	}
	
	@GetMapping("/getBill")
	public float getBilldata() {
		return cartRepository.fetchBill();
	}
		
	@PostMapping("/addFeedback")
	public String addFeedback(@RequestBody Feedback feedback) {
		feedbackService.save(feedback);
		return "feedback Added Successfully";
	}
	
	@ResponseBody
	@GetMapping("/orderComponents/{id}")
	public List<Component> fetchOrderComponents(@PathVariable int id) {
	    return componentService.fetchOrderComponents(id);
	}
}
