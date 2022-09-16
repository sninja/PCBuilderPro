package com.cdac.entity;


import javax.persistence.*;

@Entity
@Table(name = "Component")
public class Component {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int compId;
	
	private String name;
	private String category;
	private float price;
	private String status;
	
	/*
	 * @ManyToMany(mappedBy = "components") private List<Order> orders;
	 */

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
