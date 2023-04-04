package com.app.shop.orderservice.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.orderservice.client.customer.CustomerServiceClient;
import com.app.shop.orderservice.client.products.ProductServiceClient;
import com.app.shop.orderservice.data.OrderedItemsRepository;
import com.app.shop.orderservice.entity.OrderedProductEntity;
import com.app.shop.orderservice.model.OrderedProductRequest;
import com.app.shop.orderservice.model.OrderedProductsResponse;
import com.app.shop.orderservice.model.OrderedProducts;
import com.app.shop.orderservice.model.PaymentStatus;
import com.app.shop.orderservice.client.products.Product;
import com.app.shop.orderservice.error.NotFoundException;
import com.app.shop.orderservice.client.customer.Customer;


@Service
public class OrderedItemsService {


	private  OrderedItemsRepository orderedItemsRepository;
	private CustomerServiceClient customerClient;
	private ProductServiceClient productClient;
	private final static Double TAX=7.2;
	private Logger logger = LoggerFactory.getLogger(OrderedItemsService.class);

	@Autowired
	public OrderedItemsService(OrderedItemsRepository orderedItemsRepository,CustomerServiceClient customerClient,ProductServiceClient productClient) {
		this.orderedItemsRepository = orderedItemsRepository;
		this.customerClient=customerClient;
		this.productClient=productClient;
	}



	@Transactional
	public OrderedProductsResponse addOrderedItemsToCart(OrderedProductRequest orderedItemRequest) {
		logger.info(" inside OrderedItemsService- addOrderedItemsToCart");
		
		OrderedProductEntity orderedItems=new OrderedProductEntity();
		OrderedProductsResponse orderedItemResponse=null;
		List<Product> inventoryProductList=productClient.getAll();
		logger.info(" inside OrderedItemsService- addOrderedItemsToCart inventoryProductList:"+inventoryProductList.size());
		Map<Long,Integer> inventoryMap=checkAvailabilityInInventory(inventoryProductList,orderedItemRequest.getListOfProducts());
		Integer outOfStockItem= 0;//inventoryMap.entrySet().stream().findFirst().get().getValue();
		logger.info(" inside OrderedItemsService- outOfStockItem:"+outOfStockItem);
		if (outOfStockItem==0)
			throw new NotFoundException("out Of Stock ProductIten " );
		else {
			Customer customer=customerClient.getCustomer(orderedItemRequest.getCustomerId());
			String paymentTimeStamp=formatPaymentDate(orderedItemRequest.getOrderedTimeStamp());
			if(customer!=null && customer.getCustomerId()!=0)
			{
				logger.info(" inside OrderedItemsService- addOrderedItemsToCart orderedItems");
				orderedItems.setCustomerId(customer.getCustomerId());
				orderedItems.setFirstName(customer.getFirstName());
				orderedItems.setLastName(customer.getLastName());
				orderedItems.setEmailAddress(customer.getEmailAddress());
				orderedItems.setShippingAddress(customer.getAddress());
				orderedItems.setPhoneNumber(customer.getPhoneNumber());
				orderedItems.setOrderedAmount(calculateTotalBill(orderedItemRequest.getListOfProducts()).toString());
				orderedItems.setOrderedStatus(PaymentStatus.SUCCESS.name());

			}

			orderedItemsRepository.save(orderedItems);
			orderedItemResponse=new OrderedProductsResponse(getTransactionNumber(orderedItemRequest.getCustomerId()),orderedItemRequest.getCustomerId(),orderedItemRequest.getListOfProducts(),orderedItemRequest.getOrderedTimeStamp(),
					customer.getAddress(),calculateTotalBill(orderedItemRequest.getListOfProducts()),TAX,paymentTimeStamp,orderedItemRequest.getPaymentMode(),PaymentStatus.SUCCESS);
		}	
		return orderedItemResponse;
	}

	private Map<Long, Integer> checkAvailabilityInInventory(List<Product> inventoryProductList,
			List<OrderedProducts> listOfProducts) {
		Map<Long, Integer> inventoryMap=new HashMap<>();
		for(OrderedProducts orderedProduct:listOfProducts)
		{

		}
		return null;
	}





	private String formatPaymentDate(String dateString)
	{
		if (!StringUtils.hasLength(dateString)) {
			Date date = new Date(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateString = dateFormat.format(date);
		}
		return dateString;
	}

	private Double calculateTotalBill(List<OrderedProducts> listOfProducts) {
		Double billAmount = null;
		for(OrderedProducts orderedProduct:listOfProducts)
		{
			if(orderedProduct.getProductId()!=0)
			{
				double priceOfEachProduct=orderedProduct.getProductQuantity()*orderedProduct.getProductPrice();
				billAmount+=priceOfEachProduct;
			}
		}
		logger.info(" inside OrderedItemsService- billAmount:"+billAmount);
		return billAmount;
	}





	private long getTransactionNumber(long customerId) {
		List<OrderedProductEntity> orderedItemList=orderedItemsRepository.findByCustomerId(customerId);
		return orderedItemList.stream().map(OrderedProductEntity:: getTransactionId).findFirst().get();
	}



	public List<OrderedProductEntity> getOrderHistoryForCustomer(Long customerId) {
		return this.orderedItemsRepository.findByCustomerId(customerId);

	}



	public Customer getCustomerById(Long id) {
		logger.info(" inside OrderedItemsService- getCustomerById:"+id);
		return this.customerClient.getCustomer(id);
	}



	public Product getProductsById(Long id) {
		logger.info(" inside OrderedItemsService- getProductsById:"+id);
		return this.productClient.getProduct(id);
	}


	public List<Product> getProductsByCategory(String category) {
		logger.info(" inside OrderedItemsService- getProductsByCategory:"+category);
		return this.productClient.getProductsByCategory(category);
	}
}
