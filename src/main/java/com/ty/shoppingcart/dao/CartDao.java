package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Cart;
import com.ty.shoppingcart.repository.CartRepository;

@Repository
public class CartDao {

	@Autowired
	CartRepository cartRepository;

	public Cart saveCart(Cart cart) {

		return cartRepository.save(cart);
	}

	public Cart getCart(int id) {

		Optional<Cart> optional = cartRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Cart> getAllCart() {
		return cartRepository.findAll();
	}

	public Cart updateCart(int id, Cart cart) {
		Optional<Cart> optional = cartRepository.findById(id);
		if (optional.isPresent()) {
			return cartRepository.save(cart);
		} else {
			return null;
		}
	}

	public boolean deleteCart(int id) {
		Optional<Cart> optional = cartRepository.findById(id);
		if (optional.isPresent()) {
			cartRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}
