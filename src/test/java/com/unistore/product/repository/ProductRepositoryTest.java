package com.unistore.product.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void fetch() {
    }

    @Test
    void update() {
    }
}