package com.app.pojo;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.pojo.Orders;
import com.app.pojo.PaymentStatus;
import com.app.pojo.PaymentType;
import com.app.pojo.User;

@Entity
@Table
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentType paymentType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentStatus paymentStatus;
	public Payment() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	
	public Payment(PaymentType paymentType, LocalDate paymentDate, PaymentStatus paymentStatus) {
		super();
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}


	@OneToOne
	@JoinColumn(name = "order_id")
	private Orders selectedOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable=false)
	private User user;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public PaymentType getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public Orders getSelectedOrder() {
		return selectedOrder;
	}


	public void setSelectedOrder(Orders selectedOrder) {
		this.selectedOrder = selectedOrder;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
