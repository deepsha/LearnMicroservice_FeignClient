package com.app.shop.customerservice.data;

import org.springframework.data.repository.CrudRepository;

import com.app.shop.customerservice.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmailAddress(String emailAddress);
    Iterable<Customer> findByCountry(String country);
    
    
}
