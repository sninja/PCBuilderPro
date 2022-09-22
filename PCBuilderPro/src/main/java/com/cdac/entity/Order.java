package com.cdac.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "Order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date orderDate = new Date(System.currentTimeMillis());
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date deliveredDate;
	
	private float bill;
	private String status;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Feedback feedback;

	@ManyToOne
	@JoinColumn(name = "cust_Id")
	@JsonBackReference
	private Customer customer;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_component",
		joinColumns = @JoinColumn(name = "order_Id"),
		inverseJoinColumns = @JoinColumn(name = "comp_Id"))
	private List<Component> components;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public java.util.Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(java.util.Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}	
}
