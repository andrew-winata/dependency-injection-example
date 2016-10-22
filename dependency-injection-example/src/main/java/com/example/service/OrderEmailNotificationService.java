package com.example.service;

import com.example.model.Customer;
import com.example.model.Order;
import com.example.repository.CustomerRepository;
import com.example.webservice.EmailEngineApi;

public class OrderEmailNotificationService implements OrderNotificationService {

	private EmailEngineApi emailEngineApi;
	private CustomerRepository customerRepository;

	// Dependency Injection by setter or property
	public void setEmailEngineApi(EmailEngineApi emailEngineApi) {
		this.emailEngineApi = emailEngineApi;
	}

	// Dependency Injection by setter or property
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void sendNotificationToCustomer(Order order) {
		Customer customer = customerRepository.findById(order.getCustomerId());
		emailEngineApi.sendEmail("no-reply@blibli.com", customer.getEmail(), "Hai, " + customer.getName()
				+ ", kami telah menerima pesanan anda.");
	}

}
