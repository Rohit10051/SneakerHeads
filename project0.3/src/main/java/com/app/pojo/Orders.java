package com.app.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Orders
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double totalPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate  deliveryDate;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@Enumerated(EnumType.STRING)
	private Type type;
	
	
	public Orders()
	{
		super();
		System.out.println("THe order Details");
	}
	
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)//fetch=FetchType.EAGER
	@JsonIgnore
	List<OrderDetails> orderDetails=new ArrayList<>();
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)//fetch=FetchType.EAGER
	@JsonIgnore
	List<Rent> rentOrder =new ArrayList<>();

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	private User user;



	

	public Orders(int id, double totalPrice, LocalDate orderDate, LocalDate deliveryDate, OrderStatus status, Type type) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.type =type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


}
