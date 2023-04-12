package com.app.shop.customerservice;


import com.app.shop.customerservice.controller.CustomerServiceControllerIntegerationTest;
import com.app.shop.customerservice.service.CustomerServiceIntegrationTest;
import com.app.shop.customerservice.repository.CustomerServiceRepositoryIntegrationTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({CustomerServiceControllerIntegerationTest.class,CustomerServiceIntegrationTest.class,CustomerServiceRepositoryIntegrationTest.class})
public class CustomerServiceFeatureTests {

	// intentionally empty - Test Suite Setup (annotations) is sufficient
}
