package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Order;
import com.ty.shoppingcart.repository.OrderRepository;

@Repository
public class OrderDao {
	
	@Autowired
	OrderRepository orderRepository;

	public Order saveOrder(Order order) {

		return orderRepository.save(order);
	}

	public Order getOrder(int id) {

		Optional<Order> optional = orderRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	public Order updateOrder(int id, Order order) {
		Optional<Order> optional = orderRepository.findById(id);
		if (optional.isPresent()) {
			return orderRepository.save(order);
		} else {
			return null;
		}
	}

	public boolean deleteOrder(int id) {
		Optional<Order> optional = orderRepository.findById(id);
		if (optional.isPresent()) {
			orderRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}
