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

    private final CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public Customer getCustomerByEmail(@RequestParam(value = "emailAddress", required = false) String emailAddress) {
    	logger.info("within customer-service CustomerController getCustomerByEmail:");
    	return customerService.getCustomerByEmail(emailAddress);    
    }
    @GetMapping("/country")
    public Iterable<Customer> getCustomersByCountry(@RequestParam(value = "country", required = false) String country) {
    	logger.info("within customer-service CustomerController getCustomersByCountry:"+country);
    	return customerService.getCustomersByCountry(country);    
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return  customerService.addCustomer(customer);  
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
    	logger.info("within customer-service CustomerController getCustomer id:"+id);
    	return  customerService.getCustomer(id);      	
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
    	logger.info("within customer-service CustomerController updateCustomer:");
    	customerService.updateCustomer(id,customer);          	
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable("id") Long id) {
    	logger.info("within customer-service CustomerController deleteCustomer:");
    	customerService.deleteCustomer(id);             
    }
}
