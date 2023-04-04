package com.app.shop.orderservice.client.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceFallback implements CustomerServiceClient{

	@Override
	public List<Customer> getAll() {
		return new ArrayList<>();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return new Customer();
	}

	@Override
	public Customer getCustomer(long id) {
		return new Customer();
	}

	@Override
	public void updateCustomer(long id, Customer customer) {		
	}

	@Override
	public void deleteCustomer(long id) {		
	}

}
