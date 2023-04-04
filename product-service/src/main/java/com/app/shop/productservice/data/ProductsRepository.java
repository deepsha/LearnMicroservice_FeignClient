package com.app.shop.productservice.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Products, Long> {

	List<Products> getProductsByProductCategory(String productCategory);
   
    
    
}
