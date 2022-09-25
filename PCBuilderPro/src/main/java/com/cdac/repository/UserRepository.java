package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	User findByEmail(String email);

	boolean existsByEmail(String email);
	
	@Query(value = "SELECT user.*\r\n"
			+ "FROM user\r\n"
			+ "INNER JOIN user_roles\r\n"
			+ "ON user.id = user_roles.user_id\r\n"
			+ "WHERE user_roles.role_id = :id;", nativeQuery = true)
	public List<User> findByRole(@Param("id") int id);
}
 