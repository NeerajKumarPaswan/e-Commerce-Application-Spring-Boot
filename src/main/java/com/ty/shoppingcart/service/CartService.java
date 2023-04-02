package com.ty.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.CartDao;
import com.ty.shoppingcart.dao.CustomerDao;
import com.ty.shoppingcart.dao.ItemDao;
import com.ty.shoppingcart.dao.ProductDao;
import com.ty.shoppingcart.dto.Cart;
import com.ty.shoppingcart.dto.Customer;
import com.ty.shoppingcart.dto.Item;
import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class CartService {

	@Autowired
	CartDao cartDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	ItemDao itemDao;

	public ResponseStructure<Cart> saveCart(Cart cart, int cid) {
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Customer customer = customerDao.getCustomer(cid);
		cart.setCustomer(customer);
		Cart cart1 = cartDao.saveCart(cart);
		if (cart1 != null) {
			responseStructure.setData(cart1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;

	}

	public ResponseStructure<Cart> getCart(int id) {

		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Cart cart = cartDao.getCart(id);
		if (cart != null) {
			responseStructure.setData(cart);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<List<Cart>> getAllCart() {
		ResponseStructure<List<Cart>> responseStructure = new ResponseStructure<List<Cart>>();
		List<Cart> cartes = cartDao.getAllCart();
		if (cartes.size() > 0) {
			responseStructure.setData(cartes);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Cart> updateCart(int id, Cart cart) {
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Cart cart1 = cartDao.updateCart(id, cart);
		if (cart1 != null) {
			responseStructure.setData(cart1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteCart(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean flag = cartDao.deleteCart(id);
		if (flag) {
			responseStructure.setData("Data deleted");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
		} else {
			throw new NoIdFoundException("Cart id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<Cart> addProduct(Item i,int cid, int pid) {
		ResponseStructure<Cart> responseStructure = new ResponseStructure<Cart>();
		Customer customer = customerDao.getCustomer(cid);
		Cart cart = customer.getCart();
		Product product = productDao.getProduct(pid);
		if(cart!=null) {
			List<Item> items = cart.getItems();
			i.setProduct_name(product.getProduct_name());
			i.setBrand(product.getBrand());
			i.setCost(i.getQuantity()*product.getCost());
			i.setType(product.getType());
			i.setMerchant(product.getMerchant());
			i.setStatus("booked");
			int a = product.getQuantity();
			a-=i.getQuantity();
			product.setQuantity(a);
			productDao.updateProduct(pid, product);
			items.add(i);
			itemDao.saveItem(i);
			Cart cart1 = cartDao.updateCart(cart.getId(), cart);
			if (cart1 != null) {
				responseStructure.setData(cart1);
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Updated");
			} else {
				throw new NoIdFoundException();
			}
		}else {
			Cart cart2 = new Cart();
			cartDao.saveCart(cart2);
			customer.setCart(cart2);
			Customer customer2=customerDao.updateCustomer(cid, customer);
			Cart cart3=customer2.getCart();
			List<Item> items = new ArrayList<>();
			i.setProduct_name(product.getProduct_name());
			i.setBrand(product.getBrand());
			i.setCost(i.getQuantity()*product.getCost());
			i.setType(product.getType());
			i.setMerchant(product.getMerchant());
			i.setStatus("booked");
			int a = product.getQuantity();
			a-=i.getQuantity();
			product.setQuantity(a);
			productDao.updateProduct(pid, product);
			items.add(i);
			itemDao.saveItem(i);
			cart3.setItems(items);
			Cart cart1 = cartDao.updateCart(cart3.getId(), cart3);
			if (cart1 != null) {
				responseStructure.setData(cart1);
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Updated");
			} else {
				throw new NoIdFoundException();
			}
		}
		
		return responseStructure;
	}

}
