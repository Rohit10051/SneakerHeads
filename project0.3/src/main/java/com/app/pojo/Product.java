package com.app.pojo;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Product
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	private int id;
	@Column(length=10)
	private String productName;
	@Column(length=100)
	private String description;
	@Lob
	private byte[] image;
	private int qty;
	private double price;
	private double finalPrice;
	private int discount;
	private int rating;
	private int size;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Type type;
	@ManyToOne(fetch=FetchType.LAZY)//relationship with categories
	@JoinColumn(name="category_id",nullable=false)
	@JsonIgnoreProperties("products")
	private Categories categories;
	
	public Product()
	{
		System.out.println("in ctor of "+getClass().getName());
	}
	public Product(int id, String productName, String description, int qty, double price, double finalPrice, int discount, int rating, int size, byte[] image, Type type)
	{
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.qty = qty;
		this.price = price;
		this.discount = discount;
		this.rating = rating;
		this.size = size;
		this.finalPrice = finalPrice;
		this.image = image;
		this.type = type;

	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
}
