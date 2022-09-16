package com.cdac.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdac.entity.Order;

public interface OrderRepository extends CrudRepository<Order,Integer>{

}
