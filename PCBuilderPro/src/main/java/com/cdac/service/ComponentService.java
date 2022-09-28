	package com.cdac.service;

import java.util.List;
import java.util.Optional;

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
	
	public Component fetchbyId(int id) {
		Optional<Component> component = componentRepository.findById(id);
		return component.get();
		
	}
	
	public List<Component> fetchAllComponents(){
		return (List<Component>) componentRepository.findAll();
	}
	
	
	public void updateById(int id, Component component) {
		Optional<Component> component1 = componentRepository.findById(id);
		if(component1.isPresent()) {
			Component dbcomponent = component1.get();
			dbcomponent.setName(component.getName());
			dbcomponent.setCategory(component.getCategory());
			dbcomponent.setPrice(component.getPrice());
			dbcomponent.setDescription(component.getDescription());
			dbcomponent.setQuantity(component.getQuantity());
			//dbcomponent.setLink(component.getLink());
			componentRepository.save(dbcomponent);
		}
	}
	

	public void remove(int id) {
		componentRepository.deleteById(id);
	}
	
	public List<Component> fetchOrderComponents(int id){
		return (List<Component>) componentRepository.fetchComponentofOrder(id);
	}
	
	//method to update stock of components after checkout
	public void stockUpdate(List<Component> list) {
		for(int i=0; i<list.size(); i++) {
			int q = list.get(i).getQuantity();
			System.out.println("checking quantity: " + q);
			list.get(i).setQuantity(--q);
			componentRepository.save(list.get(i));
		}
	}

}
