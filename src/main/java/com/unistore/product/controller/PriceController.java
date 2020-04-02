package com.unistore.product.controller;

import com.unistore.product.domain.ProductPrice;
import com.unistore.product.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/price")
public class PriceController {
    @Autowired
    PriceService priceService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductPrice postProductPrice(@RequestBody @Valid ProductPrice productPrice) throws Exception {
        priceService.createProductPrice(productPrice);
        return productPrice;
    }


}
