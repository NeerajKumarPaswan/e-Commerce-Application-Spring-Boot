package com.ty.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.AddressDao;
import com.ty.shoppingcart.dao.CustomerDao;
import com.ty.shoppingcart.dto.Address;
import com.ty.shoppingcart.dto.Customer;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class AddressService {

	@Autowired
	AddressDao addressDao;
	@Autowired
	CustomerDao customerDao;

	public ResponseStructure<Address> saveAddress(Address address, int id) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
		Customer customer = customerDao.getCustomer(id);
		address.setCustomer(customer);
		Address address2 = addressDao.saveAddress(address);
		if (address2 != null) {
			responseStructure.setData(address2);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Address> getAddress(int id) {

		ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
		Address address = addressDao.getAddress(id);
		if (address != null) {
			responseStructure.setData(address);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Address>> getAllAddress(){
		ResponseStructure<List<Address>> responseStructure = new ResponseStructure<List<Address>>();
		List<Address> addresses = addressDao.getAllAddress();
		if (addresses.size()>0) {
			responseStructure.setData(addresses);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Address> updateAddress(int id, Address address) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<Address>();
		Address address1 = addressDao.updateAddress(id, address);
		if (address1 != null) {
			responseStructure.setData(address1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteAddress(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean flag = addressDao.deleteAddress(id);
		if (flag) {
			responseStructure.setData("Data deleted");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
		} else {
			throw new NoIdFoundException("Address id " + id + " Does not exist");
		}
		return responseStructure;
	}

}
