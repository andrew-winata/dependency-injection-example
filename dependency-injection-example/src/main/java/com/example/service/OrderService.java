package com.example.service;

import java.util.List;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.Product;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;

public class OrderService {
	private ProductRepository productRepository;
	private OrderRepository orderRepository;
	private OrderNotificationService orderNotificationService;
	
	// Dependency Injection by setter or property
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// Dependency Injection by setter or property
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	// Dependency Injection by setter or property
	public void setOrderNotificationService(OrderNotificationService orderNotificationService) {
		this.orderNotificationService = orderNotificationService;
	}

	public void createOrder(Order order) throws Exception {
		List<OrderItem> items = order.getItems();
		for (OrderItem item : items) {
			Product savedProduct = productRepository.findById(item.getProductId());
			if (savedProduct.getStock() < item.getQuantity()) {
				throw new Exception("Stock unavailable");
			}
		}
		orderRepository.create(order);
		orderNotificationService.sendNotificationToCustomer(order);
	}
}
