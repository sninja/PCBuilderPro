package com.cdac;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cdac.entity.Component;
import com.cdac.entity.Customer;
import com.cdac.entity.Order;
import com.cdac.repository.ComponentRepository;
import com.cdac.repository.CustomerRepository;
import com.cdac.repository.OrderRepository;

@SpringBootTest
class PcBuilderProApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ComponentRepository componentRepository;

	@Test
	void addCustomer() {
		Customer customer = new Customer();
		customer.setName("Sopan Patil");
		customer.setEmail("sopan@gmail.com");
		customer.setPassword("1234");
		customer.setMobile("1234567890");
		customer.setAddress("Navi Mumbai, Maharashtra - 400702");
		customerRepository.save(customer);
	}

	
	@Test 
	void addOrder() { Customer customer =
			customerRepository.findById(1).get(); Order order = new Order();

		/*
		 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); Date date =
		 * dateFormat.parse("14/09/2022"); long time = date.getTime();
		 */

					order.setOrderDate(new java.util.Date());


					/* Date date2 = dateFormat.parse("20/09/2022"); long time2 = date2.getTime(); */

					order.setDeliveredDate(new java.util.Date()); 
					order.setBill(57980.5f);
					order.setStatus("Processing"); 
					order.setCustomer(customer);
					orderRepository.save(order); 
					//p.setDateOfBirth(LocalDate.of(1999, 12, 25));

	}
	 

	@Test
	void addComponent() {
		Component component = new Component();
		  component.setName("Gigabyte NVIDIA GeForce RTX 3060 Ti");
		  component.setCategory("GPU"); 
		  component.setPrice(35862);
		  component.setStatus("available"); 
		  componentRepository.save(component);
	}
}
