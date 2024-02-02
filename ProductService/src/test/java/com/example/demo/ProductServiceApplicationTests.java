package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceApplicationTests {
	@MockBean
	private ProductService productService;
	@Test
	void contextLoads() {
	}
	@Test
	public void testaddProduct(){
		Product product=new Product();
		assertEquals(productService.addProduct(product),"Product added successfully");
	}
}
