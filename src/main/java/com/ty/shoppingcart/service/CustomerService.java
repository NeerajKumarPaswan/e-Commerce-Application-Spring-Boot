package com.ty.shoppingcart.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.AddressDao;
import com.ty.shoppingcart.dao.CustomerDao;
import com.ty.shoppingcart.dao.ProductDao;
import com.ty.shoppingcart.dto.Address;
import com.ty.shoppingcart.dto.Cart;
import com.ty.shoppingcart.dto.Customer;
import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.dto.WishList;
import com.ty.shoppingcart.exception.InvalidCredentialsException;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	@Autowired
	AddressDao addressDao;
	@Autowired
	ProductDao productDao;

	@Autowired
	MailSenderService mailSenderService;

	public ResponseStructure<Customer> saveCustomer(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		customer.setToken(generateToken());
		WishList wishList = customer.getWishList();
		wishList.setCustomer(customer);
		Customer customer2 = customerDao.saveCustomer(customer);
		if (customer2 != null) {
			responseStructure.setData(customer2);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;

	}

	public ResponseStructure<Customer> getCustomer(int id) {

		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer = customerDao.getCustomer(id);
		if (customer != null) {
			responseStructure.setData(customer);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}
	
	public boolean checkToken(Customer customer , String token) {
		if(customer.getToken().equals(token)) {
			return true;
		}else {
			return false;
		}
	}
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();
		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
	}

	public ResponseStructure<Customer> updateCustomer(int id, Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		customer.setToken(null);
		Customer customer1 = customerDao.updateCustomer(id, customer);
		if (customer1 != null) {
			responseStructure.setData(customer1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteCustomer(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean flag = customerDao.deleteCustomer(id);
		if (flag) {
			responseStructure.setData("Data deleted");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
		} else {
			throw new NoIdFoundException("Customer id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<Customer> validateCustomer(String email, String password) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer = customerDao.validateCustomer(email, password);
		if (customer != null) {
			customer.setStatus("Active");
			Customer customer1 = customerDao.updateCustomer(customer.getId(), customer);
			responseStructure.setData(customer1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new InvalidCredentialsException();
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Address>> getAllCustomerAdddresses(int id){
		ResponseStructure<List<Address>> responseStructure = new ResponseStructure<List<Address>>();
		List<Address> addresses = addressDao.getAllAddress().stream().filter(p->p.getCustomer().getId()==id).collect(Collectors.toList());
		if (addresses.size()>0) {
			responseStructure.setData(addresses);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Addresses of Given id");
		} else {
			throw new InvalidCredentialsException("No Address Found");
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Product>> viewAllProducts(){
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> products = productDao.getAllProduct().stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
		if (products.size()>0) {
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Available Products");
		} else {
			throw new InvalidCredentialsException("No Products Found");
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Product>> viewProductByBrand(String brand){
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> products = productDao.getAllProduct().stream().filter(p->p.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
		if (products.size()>0) {
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Available Products By Brand");
		} else {
			throw new InvalidCredentialsException("No Products Found");
		}
		return responseStructure;
	}

	public ResponseStructure<List<Product>> viewProductByName(String name){
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> products = productDao.getAllProduct().stream().filter(p->p.getProduct_name().equalsIgnoreCase(name)).collect(Collectors.toList());
		if (products.size()>0) {
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Available Products By name");
		} else {
			throw new InvalidCredentialsException("No Products Found");
		}
		return responseStructure;
	}
	public ResponseStructure<List<Product>> viewProductByType(String type){
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> products = productDao.getAllProduct().stream().filter(p->p.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
		if (products.size()>0) {
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Available Products By Type");
		} else {
			throw new InvalidCredentialsException("No Products Found");
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Product>> viewProductByCost(double cost){
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> products = productDao.getAllProduct().stream().filter(p->p.getCost()<=cost).collect(Collectors.toList());
		if (products.size()>0) {
			responseStructure.setData(products);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Available Products below the price mentioned");
		} else {
			throw new InvalidCredentialsException("No Products Found");
		}
		return responseStructure;
	}
	
}
