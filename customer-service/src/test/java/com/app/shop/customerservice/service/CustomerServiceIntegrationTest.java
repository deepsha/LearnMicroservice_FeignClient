package com.app.shop.customerservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.shop.customerservice.api.CustomerService;
import com.app.shop.customerservice.data.CustomerRepository;
import com.app.shop.customerservice.model.Customer;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CustomerServiceIntegrationTest {

    @Test
    void contextLoads() {
    }

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
    
    @SuppressWarnings("deprecation")
	@Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddCustomer_Success() {
    	Customer mockCustomer=new Customer();
    	mockCustomer.setFirstName("David");
    	mockCustomer.setLastName("Beckham");
    	mockCustomer.setEmailAddress("davobb@gmail.com");
    	mockCustomer.setPhoneNumber("32323");
    	
    	when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);
    	Customer customer1=customerService.addCustomer(mockCustomer);
    	
    	assertNotNull(customer1);
    	assertEquals("David",customer1.getFirstName());
    	assertEquals("davobb@gmail.com",customer1.getEmailAddress());
    	
    	
    }
    @Test
    public void testGetByCustomerId_Success() {
    	
    	long customerId = 1L;
    	Customer mockCustomer=new Customer();
    	mockCustomer.setCustomerId(customerId);
     	mockCustomer.setFirstName("David");
     	mockCustomer.setLastName("Beckham");
     	mockCustomer.setEmailAddress("davobb@gmail.com");
     	mockCustomer.setPhoneNumber("32323");
    	
    	
    	when(customerRepository.findById(customerId).get()).thenReturn(mockCustomer);
    //(customerService.getCustomer(customerId)).willReturn(Optional.of(mockCustomer));//
    	Customer customerExist=customerService.getCustomer(customerId);
    	
    	assertNotNull(customerExist);
    	assertEquals("David",customerExist.getFirstName());
    	assertEquals("davobb@gmail.com",customerExist.getEmailAddress());
    	
    	
    }
    
    @Test
    public void testGetByCountry_Success() {
    	
    	long customerId = 1L;
    	String findCountry="India";
    	Customer mockCustomer=new Customer();
    	mockCustomer.setCustomerId(customerId);
     	mockCustomer.setFirstName("David");
     	mockCustomer.setLastName("Beckham");
     	mockCustomer.setEmailAddress("davobb@gmail.com");
     	mockCustomer.setPhoneNumber("32323");
     	mockCustomer.setCountry("India");
    	
    	
    	when(customerRepository.findByCountry(findCountry)).thenReturn((Iterable<Customer>) mockCustomer);
    //(customerService.getCustomer(customerId)).willReturn(Optional.of(mockCustomer));//
    	Customer customerExist=customerService.getCustomer(customerId);
    	
    	assertNotNull(customerExist);
    	assertEquals("David",customerExist.getFirstName());
    	assertEquals("davobb@gmail.com",customerExist.getEmailAddress());
    	
    	
    }
}
