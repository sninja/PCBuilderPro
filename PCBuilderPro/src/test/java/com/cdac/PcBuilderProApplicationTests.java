package com.cdac;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cdac.entity.Component;
import com.cdac.entity.Order;
import com.cdac.entity.Role;
import com.cdac.entity.User;
import com.cdac.repository.ComponentRepository;
import com.cdac.repository.OrderRepository;
import com.cdac.repository.UserRepository;
import com.cdac.service.UserService;

@SpringBootTest
class PcBuilderProApplicationTests {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * @Test void addCustomer() { Customer customer = new Customer();
	 * customer.setName("Sopan Patil"); customer.setEmail("sopan@gmail.com");
	 * customer.setPassword("1234"); customer.setMobile("1234567890");
	 * customer.setAddress("Navi Mumbai, Maharashtra - 400702");
	 * customerRepository.save(customer); }
	 */

	/*
	 * @Test void addOrder() { Customer customer =
	 * customerRepository.findById(1).get(); Order order = new Order();
	 * 
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); Date date =
	 * dateFormat.parse("14/09/2022"); long time = date.getTime();
	 * 
	 * 
	 * order.setOrderDate(new java.util.Date());
	 * 
	 * 
	 * Date date2 = dateFormat.parse("20/09/2022"); long time2 = date2.getTime();
	 * 
	 * order.setDeliveredDate(new java.util.Date()); order.setBill(57980.5f);
	 * order.setStatus("Processing"); //order.setCustomer(customer);
	 * orderRepository.save(order); //p.setDateOfBirth(LocalDate.of(1999, 12, 25));
	 * 
	 * }
	 */
	
	@Test
	void addOrdertoCustomer() {
		User user = userRepository.findById(4).get();
		Order order = new Order();
		order.setOrderDate(new java.util.Date());
		order.setDeliveredDate(new java.util.Date());
		//order.setBill(45980.5f);
		order.setStatus("Delivered");
		order.setUser(user);
	}
	

	@Test
	void addComponent() {
		Component component = new Component();
		component.setName("Gigabyte NVIDIA GeForce RTX 3060 Ti");
		component.setCategory("GPU");
		component.setPrice(35862);
		//component.setStatus("available");
		componentRepository.save(component);
	}

	@Test
	void addRole() {
		Role role = new Role();
		role.setName("customer");
		userService.saveRole(role);
	}

	@Test
	void addUser() {

		User user = new User();
		user.setName("sumit singh");
		user.setStreet("127 w-1 block");
		user.setCity("Kanpur");
		user.setState("Uttar Pradesh");
		user.setPincode(208014);
		user.setEmail("sumit@gmail.com");
		user.setMobile(9415784189L);
		user.setPassword("1234");
		userService.saveUser(user);

		userService.addRoleToUser("sumit@gmail.com", "admin");
	}
}
