package com.unistore.product.controller;

import com.unistore.product.domain.Product;
import com.unistore.product.domain.ProductPrice;
import com.unistore.product.service.PriceService;
import com.unistore.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PriceService priceService;

    @GetMapping("")
    public List<Product> getAllProducts(@RequestParam(required = false, defaultValue = "10") int limit,
                                        @RequestParam(required = false, defaultValue = "0") int offset) {
        return productService.getAllProducts(limit, offset);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) throws Exception {
        productService.createProduct(product);
        return product;
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody @Valid Product product) throws Exception {
        productService.updateProduct(product);
        return product;
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/{productId}/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductPrice getProductPrice(@PathVariable String productId) throws Exception {
        return priceService.getProductPrice(productId);
    }
}
