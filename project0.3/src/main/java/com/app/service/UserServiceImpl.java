package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.app.dao.AddressRepository;
import com.app.dao.CategoryRepository;
import com.app.dao.OrdersRepository;
import com.app.dao.ProductRepository;
import com.app.dao.UserRepository;
import com.app.dto.LoginRequest;
import com.app.dto.UserDTO;
import com.app.pojo.Address;
import com.app.pojo.Orders;
import com.app.pojo.User;
import com.app.pojo.Categories;
import com.app.pojo.Product;


@Service
@Transactional
public class UserServiceImpl implements IUserServices {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressrepo;
	
	@Autowired
	private OrdersRepository orderRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public User authenticateUser(LoginRequest loginRequest) {
		return userRepo.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
	}
	
	@Override
	public String createAccount(User user) {
		User u = userRepo.save(user);
		Address add = new Address();
		add.setCity("Pune");
		add.setState("Maharashtra");
		add.setCurrentUser(u);
		addressrepo.save(add);
		return "SignUp successfully";
	}
	
	@Override
	public User editProfile(int userId, UserDTO userDTO) {
		User user = userRepo.findById(userId).get();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPhone(userDTO.getPhone());
		return user;
	}
	
	@Override
	public String changePassword(int userId, String pwd) {
		User u = userRepo.findById(userId).get();
		u.setPassword(pwd);
		return "Password Changed successfully";
	}
	
	@Override
	public Address getAddress(int userId) {
		return addressrepo.findById(userId).get();
	}
	
	@Override
	public String editAddress(int userId, Address address) {
		Address add = addressrepo.findById(userId).get();
		System.out.println("address : "+add);
		if(add != null) {
		add.setArea(address.getArea());
		add.setCity(address.getCity());
		add.setFlatNo(address.getFlatNo());
		add.setPinCode(address.getPinCode());
		add.setSocietyName(address.getSocietyName());
		add.setState(address.getState());
		}
		return "Address Changed successfully";
	}
	
	@Override
	public Address getAddressDetails(int orderId) {
		Orders od = orderRepo.findById(orderId).get();
		User u = od.getUser();		
		return addressrepo.findById(u.getId()).get();
	}
	
	@Override
	public User getUserDetails(int oId) {
		Orders o = orderRepo.findById(oId).get();
		System.out.println("User : "+o.getUser());
		return o.getUser();
	}
	
	@Override
	public Product addProduct(String CategoryName, Product product) {
		Categories c = categoryRepo.findByCategoryName(CategoryName);
		product.setCategories(c);
		Product p = productRepo.findByProductNameAndSize(product.getProductName(), product.getSize());
		if(p == null) {
			return productRepo.save(product);
		} else {
			int qty = p.getQty() + product.getQty();
			p.setQty(qty);
			return productRepo.saveAndFlush(p);
		}
	}
	
	@Override
	public String updateProduct(int productId, Product products) {
		Product product = productRepo.findById(productId).get();
		product.setProductName(products.getProductName());
		product.setDescription(products.getDescription());
		product.setPrice(products.getPrice());
		product.setDiscount(products.getDiscount());
        product.setFinalPrice(products.getFinalPrice());
        product.setQty(products.getQty());
        product.setSize(products.getSize());
		return "product updated";
	}
	
	@Override
	public Product getProductById(int productId) {
		return productRepo.findById(productId).get();
	}
	
	public String deleteProduct(int productId) {
		productRepo.deleteById(productId);
		return "Product Deleted";
	}

	@Override
	public String addAddress(int userId, Address address) {
		Address add = addressrepo.findById(userId).get();
		System.out.println("address : "+add);
		if(add != null) {
		add.setArea(address.getArea());
		add.setCity(address.getCity());
		add.setFlatNo(address.getFlatNo());
		add.setPinCode(address.getPinCode());
		add.setSocietyName(address.getSocietyName());
		add.setState(address.getState());
		}
		return "Address added Succefully";
	}
	
	@Override
	public List<Categories> getAllCategories() {
		return categoryRepo.findAll();
	}
}
