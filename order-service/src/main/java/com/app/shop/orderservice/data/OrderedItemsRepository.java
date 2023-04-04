package com.app.shop.orderservice.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.app.shop.orderservice.entity.OrderedProductEntity;

public interface OrderedItemsRepository extends CrudRepository<OrderedProductEntity, Long> {
	
	List<OrderedProductEntity> findByCustomerId(long customerId);
   
    
    
}
