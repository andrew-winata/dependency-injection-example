package com.example.service;

import com.example.model.Customer;
import com.example.model.Order;
import com.example.repository.CustomerRepository;
import com.example.webservice.SmsEngineApi;

public class OrderSmsNotificationService implements OrderNotificationService {

	private SmsEngineApi smsEngineApi;
	private CustomerRepository customerRepository;

	public void setSmsEngineApi(SmsEngineApi smsEngineApi) {
		this.smsEngineApi = smsEngineApi;
	}

	// Dependency Injection by setter or property
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void sendNotificationToCustomer(Order order) {
		Customer customer = customerRepository.findById(order.getCustomerId());
		smsEngineApi.sendSms(customer.getPhoneNumber(),
				"Hai, " + customer.getName()
				+ ", kami telah menerima pesanan anda.");

	}

}
