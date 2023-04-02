package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Customer;
import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.repository.CustomerRepository;

@Repository
public class CustomerDao {
	
	@Autowired
	CustomerRepository customerRepository;
	
	

	
	public Customer saveCustomer(Customer customer) {

		return customerRepository.save(customer);
	}

	
	public Customer getCustomer(int id) {

		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	
	public Customer updateCustomer(int id, Customer customer) {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			return customerRepository.save(customer);
		} else {
			return null;
		}
	}

	
	public boolean deleteCustomer(int id) {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			customerRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

	
	public Customer validateCustomer(String email, String password) {
		
		return customerRepository.validateCustomer(email, password);
	}
	
	
}
