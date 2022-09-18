package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Component;
import com.cdac.entity.Order;
import com.cdac.repository.ComponentRepository;

@Service
@Transactional
public class ComponentService {

	@Autowired
	private ComponentRepository componentRepository;
	
	public void save(Component component) {

		componentRepository.save(component);
	}
	
	public List<Component> fetchAllComponents(){
		return (List<Component>) componentRepository.findAll();
	}
	
	public void update(Component component) {
		componentRepository.save(component);
	}
	

	public void remove(int id) {
		componentRepository.deleteById(id);
	}
	
	public List<Component> fetchOrderComponents(){
		return (List<Component>) componentRepository.fetchComponentofOrder();
	}

}
