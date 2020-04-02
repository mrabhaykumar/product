package com.unistore.product.service;

import com.unistore.product.domain.ProductPrice;
import com.unistore.product.exception.PriceNotFoundException;
import com.unistore.product.repository.PriceRepository;
import com.unistore.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public void createProductPrice(ProductPrice productPrice) throws Exception {
        log.info("Price added for product : %s", productPrice.getPrice());
        priceRepository.insert(productPrice);
    }

    public ProductPrice getProductPrice(String productId) {
        Optional<ProductPrice> productPrice = priceRepository.fetch(productId);
        if (productPrice.isPresent()) {
            log.debug("Price for product %s  is %s ",productPrice.get().getProductId(),productPrice.get().toString());
            return productPrice.get();
        } else {
            log.error("Price not found for product : %s", productId);
            throw new PriceNotFoundException(String.format("Price not found for product : %s", productId));
        }
    }
}
