package com.unistore.product.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
