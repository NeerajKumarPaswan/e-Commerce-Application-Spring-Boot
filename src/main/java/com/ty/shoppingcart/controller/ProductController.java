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

import com.ty.shoppingcart.dto.Product;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/products/{id}")
	public ResponseStructure<Product> saveProduct(@RequestBody Product product, @PathVariable int id) {

		return productService.saveProduct(product,id);
	}

	@GetMapping("/products/{id}")
	public ResponseStructure<Product> getProduct(@PathVariable int id) {
		return productService.getProduct(id);
	}
	
	@GetMapping("/products")
	public ResponseStructure<List<Product>> getAllProduct(){
		return productService.getAllProduct();
	}

	@PutMapping("/products/{id}")
	public ResponseStructure<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/products/{id}")
	public ResponseStructure<String> deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
}
