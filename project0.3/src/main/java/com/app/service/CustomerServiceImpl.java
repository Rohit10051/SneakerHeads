package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CartRepository;
import com.app.dao.CategoryRepository;
import com.app.dao.OrderAddressRepository;
import com.app.dao.OrdersDetailsRepository;
import com.app.dao.OrdersRepository;
import com.app.dao.PaymentRepository;
import com.app.dao.ProductRepository;
import com.app.dao.RentRepository;
import com.app.dao.UserRepository;
import com.app.dto.PaymentDTO;
import com.app.pojo.Cart;
import com.app.pojo.Categories;
import com.app.pojo.OrderAddress;
import com.app.pojo.Product;
import com.app.pojo.Rent;
import com.app.pojo.Type;
import com.app.pojo.OrderDetails;
import com.app.pojo.OrderStatus;
import com.app.pojo.Orders;
import com.app.pojo.Payment;
import com.app.pojo.User;
import com.app.pojo.PaymentStatus;
import com.app.pojo.PaymentType;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OrdersRepository ordersrepo;
	
	@Autowired
	private OrdersDetailsRepository ordersDetailsRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private RentRepository rentRepo;
	
	@Autowired
	private OrderAddressRepository orderAddressRepo;
	private static int deliveryBoyId;
	
	@Override
	public List<Product> getAllProduct(int categoryId) {
		List<Product> list = productRepo.getAllProduct(categoryId);
		List<Product> lt = new ArrayList<Product>();
		int index = 0;
		for(index = 0; index < list.size(); index++) {
			Product p = list.get(index);
			if(p.getQty() > 0)
				lt.add(p);
		}
		return lt;
	}

	@Override
	public List<Product> getAllProductlowtohigh(int categoryId) {
		List<Product> list = productRepo.getAllProductlowtohigh(categoryId);
		List<Product> lt = new ArrayList<Product>();
		int index = 0;
		for(index = 0; index < list.size(); index++) {
			Product p = list.get(index);
			if(p.getQty() > 0)
				lt.add(p);
		}
		return lt;
	}

	@Override
	public List<Product> getAllProducthightolow(int categoryId) {
		List<Product> list = productRepo.getAllProducthightolow(categoryId);
		List<Product> lt = new ArrayList<Product>();
		int index = 0;
		for(index = 0; index < list.size(); index++) {
			Product p = list.get(index);
			if(p.getQty() > 0)
				lt.add(p);
		}
		return lt;
	}

	@Override
	public Product getProductById(int productId) {
		Product p = productRepo.findById(productId).get();
		Product l = new Product();
		l = p;
		return l;
	}

	@Override
	public String addProductToCart(Cart c) {
		Cart cart = cartRepo.findByProductNameAndSize(c.getProductName(), c.getSize());
		if(cart != null) {
			int q = cart.getQty();
			cart.setQty(c.getQty() + q);;
		}else {
			Cart ct = cartRepo.save(c);
			if(ct != null)
			return "!!! Items Added to Cart !!!";
		}
		
		return "product not added";
	}

	@Override
	public List<Cart> getCartByuserId(int userId) {
		return cartRepo.getCartByuserId(userId);
	}
	
	@Override
	public Double getCartTotalAmt(int userId) {
		List<Cart> c = cartRepo.getCartByuserId(userId);
		double amt = 0;
		for (Cart cart : c) {
			amt = amt  + cart.getFinalPrice() * cart.getQty();
		}
		return amt;
	}
	 
	@Override
	public Double getCartTotalSavingAmt(int userId) {
		List<Cart> c = cartRepo.getCartByuserId(userId);
		double famt = 0, tamt = 0;
		for (Cart cart : c) {
			famt = famt + cart.getFinalPrice() * cart.getQty();
			tamt = tamt + cart.getPrice() * cart.getQty();
		}
		return Math.floor(tamt - famt);
	}
	
	@Override
	public String deleteItemFromCart(String cartId) {
		cartRepo.deleteByProductName(cartId);
		return "Items Deleted from Cart";
	}
	
	@Override
	public List<Product> getProductListByName(String productName) {
		List<Product> list = productRepo.getProductListByName(productName);
		List<Product> lt = new ArrayList<Product>();
		int index = 0;
		for(index = 0; index < list.size(); index++) {
			Product p = list.get(index);
			if(p.getQty() > 0)
				lt.add(p);
		}
		return lt;
	}
	
	public int addOrder(int userId, double totalPrice) {
		Orders order = new Orders();
		order.setDeliveryDate(LocalDate.now().plusDays(3));
		order.setStatus(OrderStatus.PENDING);
		order.setType(Type.SALE);
		order.setOrderDate(LocalDate.now());
		order.setTotalPrice(totalPrice);	
		order.setUser(userRepo.findById(userId).get());
		return ordersrepo.save(order).getId();
	}
	
	public int addRentOrder(int userId, double totalPrice) {
		Orders order = new Orders();
		order.setDeliveryDate(LocalDate.now().plusDays(3));
		order.setStatus(OrderStatus.PENDING);
		order.setType(Type.RENT);
		order.setOrderDate(LocalDate.now());
		order.setTotalPrice(totalPrice);	
		order.setUser(userRepo.findById(userId).get());
		return ordersrepo.save(order).getId();
	}
	
	@Override
	public int addOrdersDetails(int userId, int orderId) {
		Orders orders = ordersrepo.findById(orderId).get();
		System.out.println("USER ID"+userId);
		User user = userRepo.findById(userId).get();
		System.out.println(user.toString());
		List<Cart> items = cartRepo.getCartByuserId(userId);
		for (Cart cart : items) {
			Product p = productRepo.findByProductNameAndSize(cart.getProductName(), cart.getSize());
			int qty = p.getQty() - cart.getQty();
			p.setQty(qty);
			OrderDetails od = new OrderDetails();
			od.setDescription(p.getDescription());
			od.setDiscount(p.getDiscount());
			od.setFinalPrice(cart.getFinalPrice());
			od.setSize(cart.getSize());
			od.setProductName(cart.getProductName());
			od.setQty(cart.getQty());
			od.setOrder(orders);
			od.setUser(user);
			System.out.println("asdfgh"+od.getUser());
			ordersDetailsRepo.save(od);
		}
		
		cartRepo.deleteByUserId(userId);
		return CustomerServiceImpl.deliveryBoyId;
	}

	public int addRentDetails(int userId, int orderId, int productId, int bookedDuration) {
		Orders orders = ordersrepo.findById(orderId).get();
		System.out.println("USER ID"+userId);
		User user = userRepo.findById(userId).get();
		System.out.println(user.toString());
			Product p = productRepo.findById(productId).get();
			int qty = p.getQty() - 1;
			p.setQty(qty);
			Rent rent = new Rent();
			rent.setDate(LocalDate.now());
			rent.setUser(user);
			rent.setOrder(orders);
			rent.setStatus(OrderStatus.PENDING);
			rent.setProductId(productId);
			rent.setRent_price(p.getPrice()*0.10);
			rent.setDeposit(p.getPrice()*0.30);
			rent.setBookDuration(bookedDuration);
			rent.setEstimatedPrice(rent.getRent_price() * bookedDuration + rent.getDeposit());
			rentRepo.save(rent);
		return CustomerServiceImpl.deliveryBoyId;
	}
	@Override
	public String addPayment(PaymentDTO paymentDTO) {
		Payment payment = new Payment();
		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentStatus(PaymentStatus.PAID);
		if(paymentDTO.getPaymentType().equals("CREDIT"))
			payment.setPaymentType(PaymentType.CREDIT);
		if(paymentDTO.getPaymentType().equals("DEBIT"))
			payment.setPaymentType(PaymentType.DEBIT);
		Orders o = ordersrepo.findById(paymentDTO.getOrderId()).get();
		User d = userRepo.findById(paymentDTO.getDeliveryBoyId()).get();
		payment.setSelectedOrder(o);
		payment.setUser(d);
		paymentRepo.save(payment);
		return "Payment Done";
	}

	@Override
	public List<Orders> getOrdersList(int userId) {
		User u = userRepo.findById(userId).get();
		return ordersrepo.findByUser(u);
	}
	
	@Override
	public List<OrderDetails> getOrdersDetailsList(int orderId) {
		List<OrderDetails> list = ordersDetailsRepo.findByOrder(ordersrepo.findById(orderId).get());
		System.out.println("list : "+ list);
		return list;
	}
	
	@Override
	public String updateCartItems(int userId) {
		List<Cart> list = cartRepo.findByUserId(9999);
		for (Cart cart : list) {
			cart.setUserId(userId);
		}
		return "Cart Updated";
	}
	
	@Override
	public String deliveredOrder(int orderId) {
		Orders o = ordersrepo.findById(orderId).get();
		o.setStatus(OrderStatus.DELIVERED);
		o.setDeliveryDate(LocalDate.now());
		return "Order delivered";
	}
	
	@Override
	public List<Orders> getAllOrderList() {		
		return ordersrepo.findAll();
	}
	
	@Override
	public List<Categories> getAllCategoryList() {
		return catRepo.findAll();
	}
	
	@Override
	public List<Product> getProductsForHomePage() {
		List<Product> list = productRepo.findAll();
		
		List<Product> lt = new ArrayList<Product>();
		int index = 0;
		for(index = 0; index < list.size(); index++) {
			Product p = list.get(index);
				lt.add(p);
		}
		return lt;
	}
	
	@Override
	public int addOrderAddress(OrderAddress oa) {
		return orderAddressRepo.save(oa).getId();
	}
	
	@Override
	public String addOrderIdtoOrderAddress(int aId, int oId) {
		OrderAddress ao = orderAddressRepo.findById(aId).get();
		ao.setOrderId(oId);
		return "Done";
	}
	
	@Override
	public OrderAddress getOrderAddress(int oId) {
		
		return orderAddressRepo.findByOrderId(oId);
	}

	@Override
	public List<Product> getAllRentProducts() {
		return productRepo.findByType(Type.RENT);
	}

	@Override
	public List<Product> getAllBuyProducts() {
		return productRepo.findByType(Type.SALE);
	}

	}
	
	

