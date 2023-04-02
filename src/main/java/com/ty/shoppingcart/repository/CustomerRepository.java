package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ty.shoppingcart.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("SELECT c FROM Customer c WHERE c.email=:myemail AND c.password=:mypassword")
	Customer validateCustomer(@Param("myemail")String email ,@Param("mypassword") String password);
	
}
