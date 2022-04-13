package com.app.service;

import java.util.List;

import com.app.pojo.Product;
import com.app.pojo.Categories;
import com.app.pojo.OrderAddress;
import com.app.pojo.OrderDetails;
import com.app.pojo.Orders;
import com.app.dto.PaymentDTO;
import com.app.pojo.Cart;
import com.app.pojo.Payment;

public interface ICustomerService {
	
	List<Product> getAllProduct(int categoryId);
	
	List<Product> getAllProductlowtohigh(int categoryId);
	
	List<Product> getAllProducthightolow(int categoryId);
	
	Product getProductById(int productId);
	
	String addProductToCart(Cart c);
	
	List<Cart> getCartByuserId(int userId);
	
	Double getCartTotalSavingAmt(int userId);
	
	Double getCartTotalAmt(int userId);
	
	String deleteItemFromCart(String cartId);
	
	List<Product> getProductListByName(String productName);
	
	int addOrder(int userId, double totalPrice);
	
	int addOrdersDetails(int userId, int OrderId);
	
	String addPayment(PaymentDTO payment);
	
	List<Orders> getOrdersList(int userId);
	
	List<OrderDetails> getOrdersDetailsList(int orderId);
	
	String updateCartItems(int userId);
	
	String deliveredOrder(int orderId);
	
	List<Orders> getAllOrderList();
	
	List<Categories> getAllCategoryList();
	
	List<Product> getProductsForHomePage();
	
	int addOrderAddress(OrderAddress oa);
	
	String addOrderIdtoOrderAddress(int aId, int oId);
	
	OrderAddress getOrderAddress(int oId);
	
	List<Product> getAllRentProducts();
	
	List<Product> getAllBuyProducts();

}
