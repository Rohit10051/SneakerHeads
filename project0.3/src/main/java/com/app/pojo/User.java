package com.app.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.pojo.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	private int id;
	@Column(length=20,nullable = false)
	private String firstName;
	@Column(length=20,nullable = false)
	private String lastName;
	@Column(length=30,nullable = false)
	private String email;
	@Column(length=10,nullable = false)
	private String password;
	@Column(length=13)
	private String phone;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Role role;
	private String Address;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)//fetch=FetchType.EAGER
	List<OrderDetails> orderDetails=new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)//fetch=FetchType.EAGER
	List<Rent> rentDetails=new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)//fetch=FetchType.EAGER
	List<Orders> Order=new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	List<Payment> payments = new ArrayList<>();

	public User() {
		System.out.println("in ctor of "+getClass().getName());
	}
	public User(int id, String fname, String lname, String email, String password, String phone, Role role,String Address) {
		super();
		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.Address=Address;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fname) {
		this.firstName = fname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lname) {
		this.lastName = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	

}
