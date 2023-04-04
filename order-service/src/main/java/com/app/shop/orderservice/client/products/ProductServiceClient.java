package com.app.shop.orderservice.client.products;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(value = "product-service",url="http://localhost:8082", fallback = ProductServiceFallback.class)
public interface ProductServiceClient {

	@GetMapping("/products")
	List<Product> getAll();

	@PostMapping("/products/add")
	Product addProduct(@RequestBody Product product);

	@GetMapping("/products/{id}")
	Product getProduct(@PathVariable("id") long id);
		

	@PutMapping("/products/{id}")
	void updateProduct(@PathVariable("id") long id, @RequestBody Product product);

	@DeleteMapping("/products/{id}")
	void deleteProduct(@PathVariable("id") long id);

	@GetMapping("/products/category/{category}")
	List<Product> getProductsByCategory(String category);

}
