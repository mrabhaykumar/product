package com.unistore.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unistore.product.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    //TODO NEGATIVE TEST CASES AND LEFT OVERS.
    @LocalServerPort
    int randomServerPort;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllProducts() {

    }

    @Test
    void createProduct() throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String req = "{\n" +
                "  \"productId\": \"1234\",\n" +
                "  \"sellerId\": \"kumar\",\n" +
                "  \"title\": \"dd\",\n" +
                "  \"manufacturer\": \"sss\",\n" +
                "  \"isLowQuantity\": true,\n" +
                "  \"isSoldOut\": true,\n" +
                "  \"isBackorder\": true,\n" +
                "  \"metafields\": [\n" +
                "    {\n" +
                "      \"key\": \"Capacity\",\n" +
                "      \"value\": \"full\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"cat\",\n" +
                "      \"value\": \"dog\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"requiresShipping\": true,\n" +
                "  \"isVisible\": true,\n" +
                "  \"publishedAt\": {\n" +
                "    \"$date\": \"2020-02-12T08:05:39.743Z\"\n" +
                "  },\n" +
                "  \"createdAt\": {\n" +
                "    \"$date\": \"2010-08-23T05:53:16.134Z\"\n" +
                "  },\n" +
                "  \"updatedAt\": {\n" +
                "    \"$date\": \"2019-08-23T05:53:16.134Z\"\n" +
                "  },\n" +
                "  \"workflow\": {\n" +
                "    \"status\": \"old\"\n" +
                "  }\n" +
                "}";
        Product product = objectMapper.readValue(req,Product.class);
        final String baseUrl = "http://localhost:" + randomServerPort + "/unistore/product";
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity(baseUrl,product,Product.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
//
    }

    @Test
    void updateProduct() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("productId", "123");
        String req = "{\n" +
                "  \"productId\": \"123\",\n" +
                "  \"sellerId\": \"abhay\",\n" +
                "  \"title\": \"ss\",\n" +
                "  \"manufacturer\": \"sss\",\n" +
                "  \"isLowQuantity\": true,\n" +
                "  \"isSoldOut\": true,\n" +
                "  \"isBackorder\": true,\n" +
                "  \"metafields\": [\n" +
                "    {\n" +
                "      \"key\": \"Capacity\",\n" +
                "      \"value\": \"full\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": \"cat\",\n" +
                "      \"value\": \"dog\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"requiresShipping\": true,\n" +
                "  \"isVisible\": true,\n" +
                "  \"publishedAt\": {\n" +
                "    \"$date\": \"2020-02-12T08:05:39.743Z\"\n" +
                "  },\n" +
                "  \"createdAt\": {\n" +
                "    \"$date\": \"2010-08-23T05:53:16.134Z\"\n" +
                "  },\n" +
                "  \"updatedAt\": {\n" +
                "    \"$date\": \"2019-08-23T05:53:16.134Z\"\n" +
                "  },\n" +
                "  \"workflow\": {\n" +
                "    \"status\": \"old\"\n" +
                "  }\n" +
                "}";
        Product product = objectMapper.readValue(req,Product.class);
        final String baseUrl = "http://localhost:" + randomServerPort + "/unistore/product/123";
        final String url1 = "http://localhost:" + randomServerPort + "/unistore/product/123";
        Product response = restTemplate.getForObject(baseUrl, Product.class);
        assertEquals(response.getSellerId(),"GAG");
        restTemplate.put( baseUrl, product);
        final String url2 = "http://localhost:" + randomServerPort + "/unistore/product/123";
        Product response2 = restTemplate.getForObject(baseUrl, Product.class);
        assertEquals(response2.getSellerId(),"abhay");
    }

    @Test
    void getProduct() {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/unistore/product/123";
        Product response = restTemplate.getForObject(baseUrl, Product.class);
        assertEquals(response.getProductId(),"123");
    }

    @Test
    void getProductPrice() {
    }
}