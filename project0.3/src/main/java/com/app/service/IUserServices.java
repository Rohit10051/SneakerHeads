package com.app.service;

import java.util.List;
import com.app.dto.LoginRequest;
import com.app.dto.UserDTO;
import com.app.pojo.Address;
import com.app.pojo.Categories;
import com.app.pojo.Product;
import com.app.pojo.User;

public interface IUserServices {
	User authenticateUser(LoginRequest loginRequest);
	
	String createAccount(User user);
	
	User editProfile(int userId, UserDTO userDTO);
	
	String changePassword(int userId, String pwd);
	
	Address getAddress(int userId);
	
	String editAddress(int userId, Address address);
	
	Address getAddressDetails(int orderId);
	
	User getUserDetails(int cId);
	
	public Product addProduct(String CategoryName, Product product);
	
	Product getProductById(int productId);
	
	String deleteProduct(int productId);
	
	String addAddress(int userId, Address address);
	
	public String updateProduct(int productId, Product products);
	
	public List<Categories> getAllCategories();
}
