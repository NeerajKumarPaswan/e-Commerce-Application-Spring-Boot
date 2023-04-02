package com.ty.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shoppingcart.dto.Order;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/orders/{cid}/{aid}")
	public ResponseStructure<Order> saveOrder(@RequestBody Order order, @PathVariable int cid,@PathVariable int aid) {

		return orderService.saveOrder(order,cid , aid);
	}

	@GetMapping("/orders/{id}")
	public ResponseStructure<Order> getOrder(@PathVariable int id) {
		return orderService.getOrder(id);
	}
	
	@GetMapping("/orders")
	public ResponseStructure<List<Order>> getAllOrder(){
		return orderService.getAllOrder();
	}

	@PutMapping("/orders/{id}")
	public ResponseStructure<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {
		return orderService.updateOrder(id, order);
	}

	@DeleteMapping("/orders/{id}")
	public ResponseStructure<Order> cancelOrder(@PathVariable int id) {
		return orderService.cancelOrder(id);
	}
}
