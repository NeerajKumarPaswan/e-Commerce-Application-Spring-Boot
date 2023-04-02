package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.shoppingcart.dto.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
//	@Query("Truncate table Cart")
//	void deleteCart();
	
}
