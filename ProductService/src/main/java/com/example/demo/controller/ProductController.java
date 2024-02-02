package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/productservice")
public class ProductController {
	@Autowired
	private ProductService productservice;
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody Product product) {
		return productservice.addProduct(product);
	}
	@DeleteMapping("/deleteproduct")
	public String deleteProduct(@RequestBody Product product) {
		return productservice.deleteProduct(product);
	}
	@GetMapping("/getallproducts")
	public List<Product> getAllProducts(){
		return productservice.getAllProduct();
	}
	@DeleteMapping("deleteproductbyid/{id}")
	public String deleteProductById(@PathVariable int id) {
		return productservice.deleteProductById(id);
	}
	
}
