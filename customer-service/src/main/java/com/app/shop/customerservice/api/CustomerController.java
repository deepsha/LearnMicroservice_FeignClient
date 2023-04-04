package com.app.shop.customerservice.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.app.shop.customerservice.data.CustomerRepository;
import com.app.shop.customerservice.error.BadReqeustException;
import com.app.shop.customerservice.error.NotFoundException;
import com.app.shop.customerservice.model.Customer;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping
    public Iterable<Customer> getCustomerByEmail(@RequestParam(value = "emailAddress", required = false) String emailAddress) {
    	logger.info("within customer-service CustomerController getCustomerByEmail:");
    	if (StringUtils.hasLength(emailAddress)) {
            return this.customerRepository.findCustomersByEmailAddress(emailAddress);
        }
        return this.customerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return this.customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
    	logger.info("within customer-service CustomerController getCustomer id:"+id);
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new NotFoundException("id not found: " + id);
        }
        return customer.get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
    	logger.info("within customer-service CustomerController updateCustomer:");
    	if (id != customer.getCustomerId()) {
            throw new BadReqeustException("incoming id in body doesn't match path");
        }
        this.customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable("id") Long id) {
    	logger.info("within customer-service CustomerController deleteCustomer:");
        this.customerRepository.deleteById(id);
    }
}
