package com.example.repository;

import java.util.UUID;

import com.example.datasource.DataSourceManager;
import com.example.model.Customer;

public class CustomerRepository {
	private DataSourceManager dataSourceManager;
	
	// Dependency injection by constructor
	public CustomerRepository(DataSourceManager dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	public Customer findById(String customerId) {
		dataSourceManager.doConnect();
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setName("Budi");
		customer.setAddress("Jl. KS Tubun 2C No.8");
		customer.setEmail("budi@gdn-commerce.com");
		customer.setPhoneNumber("08781239041");
		return customer;
	}
}
