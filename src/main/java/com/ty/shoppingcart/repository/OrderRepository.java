package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shoppingcart.dto.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
