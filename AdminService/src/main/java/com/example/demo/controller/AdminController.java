package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.Product;
import com.example.demo.model.Admin;
import com.example.demo.model.Customer;
import com.example.demo.service.AdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("/adminservice")
public class AdminController {
	@Autowired
	private AdminService adminservice;
	@Autowired
	private RestTemplate resttemplate;
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody Product product) {
		return resttemplate.postForObject("http://productservice/productservice/addproduct",product,String.class);
	}
	@DeleteMapping("/deleteproductbyid/{id}")
	public String deleteProductById(@PathVariable int id) {
		resttemplate.delete("http://productservice/productservice/deleteproductbyid/{id}",id);
		return "Product deleted successfully";
	}
	@PostMapping("/addcustomer")
	public String addCustomer(@RequestBody Customer customer) {
		return resttemplate.postForObject("http://customerservice/customerservice/registercustomer", customer,String.class);
		
	}
	@DeleteMapping("/deletecustomerbyid/{id}")
	public String deleteCustomerById(@PathVariable int id) {
		resttemplate.delete("http://customerservice/customerservice/deletecustomerbyid/{id}",id);
		return "Customer deleted successfully";
	}
}
