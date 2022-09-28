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
	
	@Temporal(TemporalType.DATE)
	private java.util.Date orderDate = new Date();
	
	@Temporal(TemporalType.DATE)
	private java.util.Date deliveredDate;
	
	private String status;
	private String trasactionId;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Feedback feedback;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "cust_Id")
	@JsonBackReference
	private User user;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "order_details",
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrasactionId() {
		return trasactionId;
	}

	public void setTrasactionId(String trasactionId) {
		this.trasactionId = trasactionId;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

		
	
	
}
