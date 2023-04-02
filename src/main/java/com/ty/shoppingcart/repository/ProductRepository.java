package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shoppingcart.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
