package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	ProductRepository productRepository;

	public Product saveProduct(Product product) {

		return productRepository.save(product);
	}

	public Product getProduct(int id) {

		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public Product updateProduct(int id, Product product) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			if (product.getQuantity() == 0) {
				product.setStatus("unavailable");
			} else {
				product.setStatus("available");
			}
			return productRepository.save(product);
		} else {
			return null;
		}
	}

	public boolean deleteProduct(int id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}
