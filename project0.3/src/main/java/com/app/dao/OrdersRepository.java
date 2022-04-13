package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Orders;
import com.app.pojo.User;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findByUser(User user);
	
//List<Orders> findBySelectedDeliveryBoy(User user);

}
