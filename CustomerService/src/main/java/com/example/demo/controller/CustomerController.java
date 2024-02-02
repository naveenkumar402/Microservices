package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Product;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
@CrossOrigin("*")
@RestController
@RequestMapping("/customerservice")
public class CustomerController {
	@Autowired
	private CustomerService customerservice;
	@Autowired
	private RestTemplate resttemplate;
	@PostMapping("/registercustomer")
	public String registerCustomer(@RequestBody Customer customer) {
		//String email=customer.getEmail();
		//String response=resttemplate.getForObject("http://customerservice/customerservice/sendOtp/"+email,String.class);
		//System.out.println(response.toString());
		return customerservice.addCustomer(customer);
	}
	@GetMapping("/getcustomer/{email}")
	public Customer getcustomerByEmail(@PathVariable String email){
		return customerservice.getCustomerByEmail(email);
	}
	@GetMapping("/getallbook")
	public List<Product> getAllBooks(){
		Product[] books=resttemplate.getForObject("http://productservice/productservice/getallproducts",Product[].class);
		return Arrays.asList(books);
	}
	@DeleteMapping("/deletecustomerbyid/{customer_id}")
	public String deleteCustomerById(@PathVariable int customer_id) {
		return customerservice.deleteCustomerById(customer_id);
	}
	
}
