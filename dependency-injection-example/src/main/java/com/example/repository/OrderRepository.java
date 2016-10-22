package com.example.repository;

import com.example.datasource.DataSourceManager;
import com.example.model.Order;

public class OrderRepository {
	private DataSourceManager dataSourceManager;
	
	// Dependency injection by constructor
	public OrderRepository(DataSourceManager dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	public void create(Order order) {
		dataSourceManager.doConnect();
		System.out.println("Order ID:" + order.getId() + " created.");
	}
}
