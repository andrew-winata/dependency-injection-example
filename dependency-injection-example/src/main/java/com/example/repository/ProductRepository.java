package com.example.repository;

import com.example.datasource.DataSourceManager;
import com.example.model.Product;

public class ProductRepository {
	
	private DataSourceManager dataSourceManager;
	
	// Dependency injection by constructor
	public ProductRepository(DataSourceManager dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	public Product findById(String productId) {
		dataSourceManager.doConnect();
		Product product = new Product();
		product.setId(productId);
		product.setStock(100);
		product.setMinimumStock(1);
		return product;
	}
}
