package com.example.demo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;


	@Test
	public void testAddProduct_Success() {
		Product product = new Product();
		product.setEmail("test@example.com");
		product.setProduct("Laptop");

		List<Product> existingProducts = new ArrayList<>();
		when(productRepository.findByEmail(product.getEmail())).thenReturn(existingProducts);

		String result = productService.addProduct(product);

		assertEquals("Product added successfully", result);

		verify(productRepository, times(1)).findByEmail(product.getEmail());
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testAddProduct_Failure_ProductExists() {
		Product product = new Product();
		product.setEmail("test@example.com");
		product.setProduct("Laptop");

		List<Product> existingProducts = new ArrayList<>();
		existingProducts.add(product);

		when(productRepository.findByEmail(product.getEmail())).thenReturn(existingProducts);

		String result = productService.addProduct(product);

		assertEquals("Product already exists", result);

		verify(productRepository, times(1)).findByEmail(product.getEmail());
		verify(productRepository, never()).save(product);
	}

	@Test
	public void testDeleteProduct_Success() {
		Product product = new Product();
		product.setEmail("test@example.com");
		product.setProduct("Laptop");

		doNothing().when(productRepository).delete(product);

		String result = productService.deleteProduct(product);

		assertEquals("Deleted successfully", result);

		verify(productRepository, times(1)).delete(product);
	}

	@Test
	public void testDeleteProduct_Failure() {
		Product product = new Product();
		product.setEmail("test@example.com");
		product.setProduct("Laptop");

		doThrow(new RuntimeException("Deletion failed")).when(productRepository).delete(product);

		String result = productService.deleteProduct(product);

		assertEquals("Deletion failed", result);

		verify(productRepository, times(1)).delete(product);
	}

	@Test
	public void testGetAllProduct() {
		List<Product> expectedProducts = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(expectedProducts);

		List<Product> result = productService.getAllProduct();

		assertEquals(expectedProducts, result);

		verify(productRepository, times(1)).findAll();
	}

	@Test
	public void testDeleteProductById_Success() {
		int productId = 1;

		doNothing().when(productRepository).deleteById(productId);

		String result = productService.deleteProductById(productId);

		assertEquals("product deleted", result);

		verify(productRepository, times(1)).deleteById(productId);
	}
	@Test
	public void testAddProductApi() {

		RestAssured.baseURI = "http://localhost:8082";

		String requestBody = "{ \"email\": \"test@example.com\", \"product\": \"TestProduct\",\"category\":\"testcategory\",\"price\":\"999.9\"}";


		Response response = given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/productservice/addproduct")
				.then()
				.extract().response();

		assertEquals(200, response.getStatusCode());
		assertEquals("Product added successfully", response.getBody().asString());
	}
}
