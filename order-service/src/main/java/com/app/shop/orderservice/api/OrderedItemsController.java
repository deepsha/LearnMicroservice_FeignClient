package com.app.shop.orderservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.app.shop.orderservice.client.customer.Customer;
import com.app.shop.orderservice.client.products.Product;
import com.app.shop.orderservice.entity.OrderedProductEntity;
import com.app.shop.orderservice.error.NotFoundException;
import com.app.shop.orderservice.model.OrderedProductRequest;
import com.app.shop.orderservice.model.OrderedProductsResponse;
import com.app.shop.orderservice.service.OrderedItemsService;
import java.util.List;


@RestController
@RequestMapping("/shop")
public class OrderedItemsController {
	

    private OrderedItemsService orderedItemsService;
    private Logger logger = LoggerFactory.getLogger(OrderedItemsController.class);
    
    @Autowired
    public OrderedItemsController(OrderedItemsService oderedItemsService) {
        this.orderedItemsService = oderedItemsService;
    }


  
    @PostMapping("/order/product/add")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderedProductsResponse addOrderedItemsToCart(@RequestBody OrderedProductRequest orderedItemRequest) {
    	logger.info("inside OrderedItemsController addOrderedItemsToCart");
    	logger.info(" inside OrderedItemsService- addOrderedItemsToCart orderedItemRequest:"+orderedItemRequest.getListOfProducts().size());
    	 if (orderedItemRequest.getCustomerId()==0 ) {
             throw new NotFoundException("customer is not found: " );
         }
    	 if (orderedItemRequest.getListOfProducts().size() == 0) {
             throw new NotFoundException("No products are added in cart" );
         }
    	
    	return orderedItemsService.addOrderedItemsToCart(orderedItemRequest);
    	
    }

   @GetMapping("/order/history/")
    public List<OrderedProductEntity> getOrderHistoryForCustomer(@RequestParam("customerId") Long customerId) {
    	logger.info("within order-service getOrderHistoryForCustomer id:"+customerId);
    	List<OrderedProductEntity> orderedHistoryList =orderedItemsService.getOrderHistoryForCustomer(customerId);
        if (orderedHistoryList.isEmpty()) {
            throw new NotFoundException("Customer has empty order history");
        }
        return orderedHistoryList;
    }
   @GetMapping("/customers/{id}")
   public Customer getCustomerById(@PathVariable("id") Long id) {
   	logger.info("within order-service getCustomerById id:"+id);
   	Customer customerDetails =orderedItemsService.getCustomerById( id);
       if (customerDetails.getCustomerId()==0) {
           throw new NotFoundException("Customer is not found");
       }
       return customerDetails;
   }
   @GetMapping("/products/{id}")
   public Product getProductsById(@PathVariable("id") Long id) {
   	logger.info("within order-service getProductsById id:"+id);
   	Product productDetails =orderedItemsService.getProductsById( id);
       if (productDetails.getProductId()==0) {
           throw new NotFoundException("Product is not found");
       }
       return productDetails;
   }
   @GetMapping("/products/category/{category}")
   public List<Product> getProductsByCategory(@PathVariable("category") String category) {
   	logger.info("within order-service getProductsByLocation id:"+category);
   	List<Product> productDetailsList =orderedItemsService.getProductsByCategory(category);
       if (productDetailsList.isEmpty()) {
           throw new NotFoundException("There is no product for that category "+category);
       }
       return productDetailsList;
   }
}
