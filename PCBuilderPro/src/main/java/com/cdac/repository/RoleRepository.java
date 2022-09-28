package com.cdac.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>{
	Role findByName(String name);
}
