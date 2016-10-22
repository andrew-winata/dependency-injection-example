package com.example.service;

import com.example.model.Order;

public interface OrderNotificationService {
	void sendNotificationToCustomer(Order order);
}
