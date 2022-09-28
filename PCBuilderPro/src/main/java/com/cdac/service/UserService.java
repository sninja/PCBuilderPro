package com.cdac.service;

import java.util.List;

import com.cdac.entity.Role;
import com.cdac.entity.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String email,String roleName);
	User getUser(String email);
	List<User> getUsers();
}
