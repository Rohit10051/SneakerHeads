package com.app.pojo;

import javax.persistence.*;

@Entity
@Table
public class OrderDetails 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String productName;
	private int qty;
	private double finalPrice;
	private String Description;
	private int Discount;
	private String rating;
	private int size;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id",nullable=false)
	private Orders order;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id",nullable=false)
	private User user;
	
	public OrderDetails() {
		// TODO Auto-generated constructor stub
		System.out.println("in ctor of "+getClass().getName());
	}

	public OrderDetails(int id, String productName, int qty, double finalPrice,String Description,int Discount,String rating,int size)
	{
		this.id = id;
		this.productName = productName;
		this.qty = qty;
		this.finalPrice = finalPrice;
		this.Description=Description;
		this.Discount=Discount;
		this.rating=rating;
		this.size=size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getDiscount() {
		return Discount;
	}

	public void setDiscount(int discount) {
		Discount = discount;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	

}
