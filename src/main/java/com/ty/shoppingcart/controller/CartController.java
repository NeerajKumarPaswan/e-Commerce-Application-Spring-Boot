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

import com.ty.shoppingcart.dto.Cart;
import com.ty.shoppingcart.dto.Item;
import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/carts/{cid}")
	public ResponseStructure<Cart> saveCart(@RequestBody Cart cart, @PathVariable int cid) {

		return cartService.saveCart(cart,cid);
	}

	@GetMapping("/carts/{id}")
	public ResponseStructure<Cart> getCart(@PathVariable int id) {
		return cartService.getCart(id);
	}
	
	@GetMapping("/carts")
	public ResponseStructure<List<Cart>> getAllCart(){
		return cartService.getAllCart();
	}

	@PutMapping("/carts/{id}")
	public ResponseStructure<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart) {
		return cartService.updateCart(id, cart);
	}

	@DeleteMapping("/carts/{id}")
	public ResponseStructure<String> deleteCart(@PathVariable int id) {
		return cartService.deleteCart(id);
	}
	
	@PutMapping("/carts/{cid}/{pid}")
	public ResponseStructure<Cart> addProduct(@RequestBody Item i,@PathVariable int cid,@PathVariable int pid){
		return cartService.addProduct(i,cid, pid);
	}
}
