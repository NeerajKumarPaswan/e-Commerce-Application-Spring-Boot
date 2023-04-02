package com.ty.shoppingcart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.AddressDao;
import com.ty.shoppingcart.dao.CartDao;
import com.ty.shoppingcart.dao.CustomerDao;
import com.ty.shoppingcart.dao.OrderDao;
import com.ty.shoppingcart.dto.Address;
import com.ty.shoppingcart.dto.Cart;
import com.ty.shoppingcart.dto.Customer;
import com.ty.shoppingcart.dto.Item;
import com.ty.shoppingcart.dto.Order;
import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	CartDao cartDao;
	
	public ResponseStructure<Order> saveOrder(Order order, int cid, int aid) {
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		Customer customer = customerDao.getCustomer(cid);
		Cart cart = customer.getCart();
		if(cart!=null) {
		List<Item> items = new ArrayList<>();
		for(Item i : cart.getItems()) {
			items.add(i);
		}
		order.setItems(items);
		order.setCustomer(customer);
		Address address = addressDao.getAddress(aid);
		if(address.getCustomer().getId()==cid) {
			order.setAddress(address);
			order.setLocalDateTime(LocalDateTime.now());
			double total = 0;
			for(Item i : items) {
				total+=i.getCost();
			}
			order.setTotal(total);
			Order order1 = orderDao.saveOrder(order);
			customer.setCart(null);
			customerDao.updateCustomer(cid, customer);
			cartDao.deleteCart(cart.getId());
			if (order1 != null) {
				responseStructure.setData(order1);
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Success");
			} else {
				throw new NoIdFoundException("Order Not Saved");
			}
		}else {
			throw new NoIdFoundException(" Wrong Address");
		}
		}else {
			throw new NoIdFoundException("Add To cart First");
		}
		return responseStructure;

	}

	public ResponseStructure<Order> getOrder(int id) {

		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		Order order = orderDao.getOrder(id);
		if (order != null) {
			responseStructure.setData(order);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<List<Order>> getAllOrder() {
		ResponseStructure<List<Order>> responseStructure = new ResponseStructure<List<Order>>();
		List<Order> orderes = orderDao.getAllOrder();
		if (orderes.size() > 0) {
			responseStructure.setData(orderes);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Order> updateOrder(int id, Order order) {
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		Order order1 = orderDao.updateOrder(id, order);
		if (order1 != null) {
			responseStructure.setData(order1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Order> cancelOrder(int id) {
		ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
		Order order = orderDao.getOrder(id);
		order.setStatus("cancelled");
		Order order2= orderDao.updateOrder(id, order);
		if (order2!=null) {
			responseStructure.setData(order2);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Order Cancelled");
		} else {
			throw new NoIdFoundException("Order id " + id + " Does not exist");
		}
		return responseStructure;
	}

}
