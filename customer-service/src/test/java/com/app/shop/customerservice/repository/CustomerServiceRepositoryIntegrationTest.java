package com.app.shop.customerservice.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.app.shop.customerservice.data.CustomerRepository;
import com.app.shop.customerservice.model.Customer;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace=Replace.AUTO_CONFIGURED)
public class CustomerServiceRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
    public void testFindByEmail_Success() {
		
		Customer mockCustomer=new Customer();
    	mockCustomer.setCustomerId(2l);
     	mockCustomer.setFirstName("David");
     	mockCustomer.setLastName("Beckham");
     	mockCustomer.setEmailAddress("davobb@gmail.com");
     	mockCustomer.setPhoneNumber("32323");
     	entityManager.persist(mockCustomer);
        // Find an inserted record
        Customer foundContact = customerRepository.findByEmailAddress("davobb@gmail.com");
        
       // assertEquals(foundContact.getEmailAddress(), "davobb@gmail.com");
        assertThat(foundContact.getEmailAddress(), is(equalTo("davobb@gmail.com")));
    }
	
}
