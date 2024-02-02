package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerrepo;
	public String addCustomer( Customer customer) {
		if(validateEmail(customer.getEmail())){
			customerrepo.save(customer);
			return "Registration successfully";
		}
		return "User already exists in this email";
	}
	public String deleteCustomerById(int id) {
		customerrepo.deleteById(id);
		return "Customer deleted successfully";
	}
	public Customer getCustomerByEmail(String email){
		return customerrepo.findByEmail(email);
	}
	public boolean validateEmail(String email){
		Customer customer=customerrepo.findByEmail(email);
		if(customer==null){
			return true;
		}
		return false;
	}
}
