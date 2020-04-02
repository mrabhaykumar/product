package com.unistore.product.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unistore.product.domain.Price;
import com.unistore.product.domain.Product;
import com.unistore.product.domain.ProductPrice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
public class PriceRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public static final String INSERT_QUERY = "INSERT INTO price (\n" +
            "product_id,\n" +
            "range,\n" +
            "min,\n" +
            "max\n" +
            ")values (\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?\n" +
            ")";
    public static final String FIND_BY_PRODUCT_ID_QUERY = "SELECT * FROM PRICE WHERE PRODUCT_ID = ?";

    public void insert(ProductPrice productPrice) throws Exception {

            jdbcTemplate.update(INSERT_QUERY, productPrice.getProductId(),
                    productPrice.getPrice().getRange(),
                    productPrice.getPrice().getMin(),
                    productPrice.getPrice().getMax());

    }
    public Optional<ProductPrice> fetch(String productId){

            List<ProductPrice> productPrices = jdbcTemplate.query(
                    FIND_BY_PRODUCT_ID_QUERY,
                    new Object[]{productId},
                    (rs, rowNum) -> getProductPrice(rs));
            if (productPrices.size() == 0) {
                return Optional.empty();
            }
            return Optional.of(productPrices.iterator().next());
        }

    private ProductPrice getProductPrice(ResultSet rs) throws SQLException {
      return   ProductPrice.builder()
                .productId(rs.getString("product_id"))
                .price(Price.builder()
                        .range(rs.getString("range"))
                        .max(rs.getDouble("max"))
                        .min(rs.getDouble("min"))
                        .build()
                ).build();
    }

}
