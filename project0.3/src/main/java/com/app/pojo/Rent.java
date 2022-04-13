package com.app.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Rent 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private Double rent_price;
	private int productId;
	private Double estimatedPrice;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	private Double deposit;
	private int bookDuration;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id",nullable=false)
	private Orders order;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id",nullable=false)
	private User user;
	
	public Rent() {
		System.out.println("in ctor of "+getClass().getName());
	}
	public Rent(int id, LocalDate date, Double rent_price, int productId, Double estimatedPrice, OrderStatus status,
			Double deposit, int bookDuration) {
		this.id = id;
		this.date = date;
		this.rent_price = rent_price;
		this.productId = productId;
		this.estimatedPrice = estimatedPrice;
		this.status = status;
		this.deposit = deposit;
		this.bookDuration = bookDuration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getRent_price() {
		return rent_price;
	}
	public void setRent_price(double d) {
		this.rent_price = d;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Double getEstimatedPrice() {
		return estimatedPrice;
	}
	public void setEstimatedPrice(Double estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public int getBookDuration() {
		return bookDuration;
	}
	public void setBookDuration(int bookDuration) {
		this.bookDuration = bookDuration;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
