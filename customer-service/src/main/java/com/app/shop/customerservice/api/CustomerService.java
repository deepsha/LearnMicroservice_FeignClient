package com.app.shop.customerservice.api;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.shop.customerservice.data.CustomerRepository;
import com.app.shop.customerservice.error.BadReqeustException;
import com.app.shop.customerservice.error.NotFoundException;
import com.app.shop.customerservice.model.Customer;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	public Customer getCustomerByEmail(String emailAddress) {

		if (StringUtils.hasLength(emailAddress)) {
			return this.customerRepository.findByEmailAddress(emailAddress);
		}
		return new Customer();
	}
	public Customer addCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}
	public Customer getCustomer(Long id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new NotFoundException("id not found: " + id);
        }
        return customer.get();
	}
	public void updateCustomer(Long id, Customer customer) {
    	if (id != customer.getCustomerId()) {
            throw new BadReqeustException("incoming id in body doesn't match path");
        }
        this.customerRepository.save(customer);
	}
	public void deleteCustomer(Long id) {
		this.customerRepository.deleteById(id);		
	}
	public Iterable<Customer> getCustomersByCountry(String country) {
			if(country.isBlank())
				return (Iterable<Customer>) new Customer();
		return customerRepository.findByCountry(country);
	}

}
