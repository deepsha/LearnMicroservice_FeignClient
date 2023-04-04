package com.app.shop.productservice.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.app.shop.productservice.data.Products;
import com.app.shop.productservice.data.ProductsRepository;
import com.app.shop.productservice.error.BadReqeustException;
import com.app.shop.productservice.error.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductsRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    public ProductController(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }


  
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Products addProducts(@RequestBody Products product) {
        return this.productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable("id") Long id) {
    	logger.info("within product-service ProductController getProduct id:"+id);
        Optional<Products> products = this.productRepository.findById(id);
        if (products.isEmpty()) {
            throw new NotFoundException("id not found: " + id);
        }
        return products.get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Products product) {
    	logger.info("within product-service ProductController updateProduct:");
    	if (id != product.getProductId()) {
            throw new BadReqeustException("incoming id in body doesn't match path");
        }
        this.productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
    	logger.info("within product-service ProductController deleteProduct:");
        this.productRepository.deleteById(id);
    }
    
    @GetMapping("category/{category}")
    List<Products> getProductsByCategory(@PathVariable("category") String category){
       	logger.info("within product-service ProductController getProductsByCategory:");
       	return this.productRepository.getProductsByProductCategory(category);
    }
}
