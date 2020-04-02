package com.unistore.product.service;

import com.unistore.product.domain.Product;
import com.unistore.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @BeforeEach
    void setup() {
    }

    @Test
    void getAllProducts() {
        Mockito.when(productRepository.findAll(0,0)).thenReturn(new ArrayList<>());
        List<Product> products = productService.getAllProducts(0,0);
        assertEquals(products.size(),0);
    }

    @Test
    void createProduct() {
    }

    @Test
    void getProduct() {
        Product product = Product.builder().productId("123").build();
        Mockito.when(productRepository.fetch("123")).thenReturn(java.util.Optional.ofNullable(product));
        Product  product1= productService.getProduct("123");
        assertEquals(product1.getProductId(),"123");
    }

    @Test
    void updateProduct() {
    }
}