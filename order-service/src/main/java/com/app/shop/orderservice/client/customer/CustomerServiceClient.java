package com.app.shop.orderservice.client.customer;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "customer-service", url="http://localhost:8081",fallback = CustomerServiceFallback.class)
public interface CustomerServiceClient {
	
	@GetMapping("/customers")
    List<Customer> getAll();

    @PostMapping("/customers")
    Customer addCustomer(@RequestBody Customer customer);

    @GetMapping("/customers/{id}")
    Customer getCustomer(@PathVariable("id") long id);

    @PutMapping("/customers/{id}")
    void updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer);

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable("id") long id);

}
