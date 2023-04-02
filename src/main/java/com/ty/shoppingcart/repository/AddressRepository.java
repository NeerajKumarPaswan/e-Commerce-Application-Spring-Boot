package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shoppingcart.dto.Address;

public interface AddressRepository  extends JpaRepository<Address, Integer>{

}
