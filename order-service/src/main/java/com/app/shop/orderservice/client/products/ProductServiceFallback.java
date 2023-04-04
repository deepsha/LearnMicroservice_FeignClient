package com.app.shop.orderservice.client.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductServiceFallback implements ProductServiceClient{

	@Override
	public List<Product> getAll() {		
		return new ArrayList<>();
	}

	@Override
	public Product addProduct(Product product) {
		return new Product();
	}

	@Override
	public Product getProduct(long id) {
		return  new Product();
	}

	@Override
	public void updateProduct(long id, Product product) {				
	}

	@Override
	public void deleteProduct(long id) {		
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		
		return new ArrayList<>();
	}

}
