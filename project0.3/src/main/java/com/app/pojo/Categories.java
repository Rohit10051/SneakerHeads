package com.app.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
@Table
public class Categories 
{	@Id//Primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column (name="category_name")
	private String categoryName;//fetch type eager & lazy
	
	@OneToMany(mappedBy="categories",cascade=CascadeType.ALL,orphanRemoval=true)//fetch=FetchType.EAGER
	List<Product> products=new ArrayList<Product>();
	
	public Categories()
	{
		super();
		System.out.println("Catogeries Details");
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
