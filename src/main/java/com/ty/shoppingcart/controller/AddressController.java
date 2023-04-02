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

import com.ty.shoppingcart.dto.Address;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/address/{id}")
	public ResponseStructure<Address> saveAddress(@RequestBody Address address, @PathVariable int id) {

		return addressService.saveAddress(address,id);
	}

	@GetMapping("/address/{id}")
	public ResponseStructure<Address> getAddress(@PathVariable int id) {
		return addressService.getAddress(id);
	}
	
	@GetMapping("/address")
	public ResponseStructure<List<Address>> getAllAddress(){
		return addressService.getAllAddress();
	}

	@PutMapping("/address/{id}")
	public ResponseStructure<Address> updateAddress(@PathVariable int id, @RequestBody Address address) {
		return addressService.updateAddress(id, address);
	}

	@DeleteMapping("/address/{id}")
	public ResponseStructure<String> deleteAddress(@PathVariable int id) {
		return addressService.deleteAddress(id);
	}
}
