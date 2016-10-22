package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.datasource.DataSourceManager;
import com.example.model.Customer;
import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.OrderEmailNotificationService;
import com.example.service.OrderService;
import com.example.service.OrderSmsNotificationService;
import com.example.webservice.EmailEngineApi;
import com.example.webservice.SmsEngineApi;

/**
 * Hello world!
 *
 */
public class App {

	private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/test_db";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "admin";
	private static final int CONNECTION_TIMEOUT = 3000;
	
	public static void main(String[] args) {
		try {
		DataSourceManager dataSourceManager = new DataSourceManager(CONNECTION_URL, USERNAME, PASSWORD,
				CONNECTION_TIMEOUT);
		
		ProductRepository productRepository = new ProductRepository(dataSourceManager);
		OrderRepository orderRepository = new OrderRepository(dataSourceManager);
		CustomerRepository customerRepository = new CustomerRepository(dataSourceManager);
		
		SmsEngineApi smsEngineApi = new SmsEngineApi();
		EmailEngineApi emailEngineApi = new EmailEngineApi();
		
		OrderSmsNotificationService orderSmsNotificationService = new OrderSmsNotificationService();
		orderSmsNotificationService.setCustomerRepository(customerRepository);
		orderSmsNotificationService.setSmsEngineApi(smsEngineApi);
		
		OrderEmailNotificationService orderEmailNotificationService = new OrderEmailNotificationService();
		orderEmailNotificationService.setCustomerRepository(customerRepository);
		orderEmailNotificationService.setEmailEngineApi(emailEngineApi);
		
		OrderService orderService = new OrderService();
		orderService.setProductRepository(productRepository);
		orderService.setOrderRepository(orderRepository);
		
		
		// Create order and send notification by email
		orderService.setOrderNotificationService(orderEmailNotificationService);
		Order order1 = createOrderData();
		orderService.createOrder(order1);
		
		
		// Create order and send notification by sms
		orderService.setOrderNotificationService(orderSmsNotificationService);
		Order order2 = createOrderData();
		orderService.createOrder(order2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Order createOrderData() {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setCustomerId(UUID.randomUUID().toString());
		List<OrderItem> orderItems = createOrderItemsData();
		order.setItems(orderItems);
		return order;
	}
	
	public static List<OrderItem> createOrderItemsData() {
		List<OrderItem> items = new ArrayList<>();
		OrderItem item1 = new OrderItem();
		item1.setId(UUID.randomUUID().toString());
		item1.setProductId(UUID.randomUUID().toString());
		item1.setQuantity(10);
		items.add(item1);
		return items;
		
	}
}
