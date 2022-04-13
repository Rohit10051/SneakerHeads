package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.OrderDetails;
import com.app.pojo.Orders;

@Repository
public interface OrdersDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	List<OrderDetails> findByOrder(Orders odr);
	
	
}
