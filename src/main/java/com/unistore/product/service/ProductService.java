package com.unistore.product.service;

import com.unistore.product.domain.Product;
import com.unistore.product.exception.ProductNotFoundException;
import com.unistore.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(int limit, int offset) {

        List<Product> products =  productRepository.findAll(limit, offset);
        log.info("Returned products size {}", products.size());
        return products;
    }

    public void createProduct(Product product) throws Exception {
        log.info("Product inserted.  product Id {}",product.getProductId());
        productRepository.insert(product);
    }

    public Product getProduct(String productId) {
        if (productRepository.fetch(productId).isPresent())
            return productRepository.fetch(productId).get();
        else {
            log.info("Products not found with product id {}",productId);
            throw new ProductNotFoundException(String.format("product with id %s not found",productId));
        }
    }

    public void updateProduct(Product product) throws Exception{
        log.info("Product update.  product Id {}",product.getProductId());
        productRepository.update(product);
    }
}
