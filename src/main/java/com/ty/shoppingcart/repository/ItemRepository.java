package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shoppingcart.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
