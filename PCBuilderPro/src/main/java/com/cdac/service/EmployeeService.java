package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Order;
import com.cdac.entity.User;
import com.cdac.repository.EmployeeRepository;
import com.cdac.repository.OrderRepository;
import com.cdac.repository.UserRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;


	public List<User> fetchAllEmployees(){
		return (List<User>)userRepository.findByRole(2);
	}

	public void updateById(int id, User user) {
		Optional<User> user1 = userRepository.findById(id);
		if(user1.isPresent()) {
			User dbUser = user1.get();
			dbUser.setName(user.getName());
			dbUser.setEmail(user.getEmail());
			dbUser.setMobile(user.getMobile());
			dbUser.setStreet(user.getStreet());
			dbUser.setCity(user.getCity());
			dbUser.setState(user.getState());
			dbUser.setPincode(user.getPincode());
			userRepository.save(dbUser);
		}
		
	}
	
	public void remove(int id) {
		userRepository.deleteById(id);
	}
}
