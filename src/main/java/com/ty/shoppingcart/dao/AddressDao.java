package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Address;
import com.ty.shoppingcart.repository.AddressRepository;

@Repository
public class AddressDao {
	
	@Autowired
	AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {

		return addressRepository.save(address);
	}
	
	public Address getAddress(int id) {

		Optional<Address> optional = addressRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Address> getAllAddress(){
		return addressRepository.findAll();
	}

	
	public Address updateAddress(int id, Address address) {
		Optional<Address> optional = addressRepository.findById(id);
		if (optional.isPresent()) {
			return addressRepository.save(address);
		} else {
			return null;
		}
	}

	
	public boolean deleteAddress(int id) {
		Optional<Address> optional = addressRepository.findById(id);
		if (optional.isPresent()) {
			addressRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}
}
