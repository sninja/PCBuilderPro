package com.cdac;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cdac.entity.Role;
import com.cdac.service.UserService;

@SpringBootApplication
public class PcBuilderProApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcBuilderProApplication.class, args);
	}

	
	/*
	 * CommandLineRunner run(UserService userService) { return args -> {
	 * userService.addRoleToUser(sumit@gmail.com, null); }; }
	 */
	 

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
