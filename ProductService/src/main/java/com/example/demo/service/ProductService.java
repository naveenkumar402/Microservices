package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productrepo;
	@Autowired
	private RestTemplate restTemplate;
	public String addProduct(Product product) {
		List<Product> existingProducts = productrepo.findByEmail(product.getEmail());
		boolean productExists = existingProducts.stream()
				.anyMatch(p -> p.getProduct().equals(product.getProduct()));

		if (productExists) {
			return "Product already exists";
		} else {
			productrepo.save(product);
			return "Product added successfully";
		}
	}
	public String deleteProduct(Product product) {
		try {
			productrepo.delete(product);
		}
		catch(Exception e) {
			return e.getMessage();
		}
		return "Deleted successfully";
	}
	public List<Product> getAllProduct(){
		List<Product> product=productrepo.findAll();
		return product;
	}
	public String deleteProductById(int id) {
		productrepo.deleteById(id);
		return "product deleted";
	}
}
