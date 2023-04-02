package com.ty.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.ProductDao;
import com.ty.shoppingcart.dao.MerchantDao;
import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	MerchantDao merchantDao;

	public ResponseStructure<Product> saveProduct(Product product, int id) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Merchant merchant = merchantDao.getMerchant(id);
		if(merchant.getStatus().equalsIgnoreCase("approved")) {
		product.setMerchant(merchant);
		Product product2 = productDao.saveProduct(product);
		if (product2 != null) {
			responseStructure.setData(product2);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException("Not Saved");
		}
		}else {
			throw new NoIdFoundException("Merchant is not Approved to add product");
		}
		return responseStructure;

	}

	public ResponseStructure<Product> getProduct(int id) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Product product = productDao.getProduct(id);
		if (product != null) {
			responseStructure.setData(product);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Product>> getAllProduct(){
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		List<Product> productes = productDao.getAllProduct();
		if (productes.size()>0) {
			responseStructure.setData(productes);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Product> updateProduct(int id, Product product) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		Merchant merchant = productDao.getProduct(id).getMerchant();
		product.setMerchant(merchant);
		Product product1 = productDao.updateProduct(id, product);
		if (product1 != null) {
			responseStructure.setData(product1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteProduct(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean flag = productDao.deleteProduct(id);
		if (flag) {
			responseStructure.setData("Data deleted");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
		} else {
			throw new NoIdFoundException("Product id " + id + " Does not exist");
		}
		return responseStructure;
	}
}
