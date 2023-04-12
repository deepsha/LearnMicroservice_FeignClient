package com.app.shop.customerservice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import com.app.shop.customerservice.api.CustomerController;
import com.app.shop.customerservice.api.CustomerService;
import com.app.shop.customerservice.model.Customer;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerServiceControllerIntegerationTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(customerController);
	}
	@Test
	public void testAddCustomer_Success() throws Exception {
		Customer mockCustomer=new Customer();		
		mockCustomer.setFirstName("David");
		mockCustomer.setLastName("Beckham");
		mockCustomer.setEmailAddress("davobb@gmail.com");
		mockCustomer.setPhoneNumber("32323");

		when(customerService.addCustomer(any(Customer.class))).thenReturn(mockCustomer);

		Customer customer1=new Customer();
		customer1.setCustomerId(4l);
		customer1.setFirstName("David");
		customer1.setLastName("Beckham");


		// simulate the form submit (POST)
		mockMvc
		.perform(post("/customers/add", customer1))
		.andExpect(status().is(400))
		.andReturn();


	}
}
