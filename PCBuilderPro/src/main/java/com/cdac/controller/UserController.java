package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Role;
import com.cdac.entity.User;
import com.cdac.exception.CustomerServiceException;
import com.cdac.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@GetMapping("/userName")
	public String getUserName(String email) {
	    return userService.getUser(email).getName();
	}
	
	//@GetMapping("/user/save")
	@PostMapping("/register/customer")
	public String saveCustomer(@RequestBody User user){
		try {
			userService.saveUser(user);
			userService.addRoleToUser(user.getEmail(), "customer");
			return "Registration successful";
		}catch(CustomerServiceException e) {
			return "registration failed";
		}	
	}
	
	@PostMapping("/register/employee")
	public String saveEmployee(@RequestBody User user){
		try {
			userService.saveUser(user);
			userService.addRoleToUser(user.getEmail(), "employee");
			return "Registration successful";
		}catch(CustomerServiceException e) {
			return "registration failed";
		}	
	}
	
	@GetMapping("/role/save")
	public ResponseEntity<Role> saveUser(@RequestBody Role role){
		return ResponseEntity.ok().body(userService.saveRole(role ));
	}
	
	@GetMapping("/role/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		userService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
	
	class RoleToUserForm {
		
		private String username;
		private String roleName;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		
		
	}
}
