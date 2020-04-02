package com.unistore.product.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unistore.product.domain.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public static final String FIND_ALL_QUERY = "SELECT * FROM product ORDER BY id ASC LIMIT ? OFFSET ?";
    public static final String FIND_BY_ID = "SELECT * FROM product WHERE product_id = ?";
    public static final String INSERT_QUERY = "INSERT INTO product( product_id, seller_id, title, " +
            "manufacturer, is_low_quantity, is_sold_out, is_back_order, metafields, " +
            "requires_shipping, is_visible, published_at, created_at, updated_at, workflow_status )" +
            "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP(), ?)";
    public static final String UPDATE_QUERY = "UPDATE  product SET  seller_id = ?, title = ?, " +
            "manufacturer = ?, is_low_quantity = ?, is_sold_out = ?, is_back_order = ?, metafields = ?, " +
            "requires_shipping = ?, is_visible = ?, published_at = ?, created_at = ?, updated_at = CURRENT_TIMESTAMP(), workflow_status = ? WHERE product_id = ?";

    public List<Product> findAll(int limit, int offset) {
        return jdbcTemplate.query(
                FIND_ALL_QUERY,
                new Object[]{limit, offset},
                (rs, rowNum) -> getProduct(rs)
        );
    }

    private Product getProduct(ResultSet rs) throws SQLException {
        String metafields = rs.getString("metafields");
        ArrayList<MetaField> metaField = new ArrayList<>();
        try {
            metaField = objectMapper.readValue(metafields, objectMapper.getTypeFactory().constructCollectionType(List.class, MetaField.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Product.builder()
                .productId(rs.getString("product_id"))
                .sellerId(rs.getString("seller_id"))
                .title(rs.getString("title"))
                .manufacturer(rs.getString("manufacturer"))
                .isLowQuantity(rs.getBoolean("is_low_quantity"))
                .isSoldOut(rs.getBoolean("is_sold_out"))
                .isBackorder(rs.getBoolean("is_back_order"))
                .metafields(metaField)
                .requiresShipping(rs.getBoolean("requires_shipping"))
                .isVisible(rs.getBoolean("is_visible"))
                .publishedAt(new PublishedAt(rs.getTimestamp("published_at")))
                .createdAt(new CreatedAt(rs.getTimestamp("created_at")))
                .updatedAt(new UpdatedAt(rs.getTimestamp("updated_at")))
                .workflow(Workflow.builder().status(rs.getString("workflow_status")).build())
                .build();
    }

    public void insert(Product product) throws Exception {
        try {
            jdbcTemplate.update(INSERT_QUERY,
                    product.getProductId(), product.getSellerId(), product.getTitle(), product.getManufacturer(),
                    product.isLowQuantity(), product.isSoldOut(), product.isBackorder(), objectMapper.writeValueAsString(product.getMetafields()),
                    product.isRequiresShipping(), product.isVisible(),
                    product.getPublishedAt().get$date(), product.getCreatedAt().get$date(), product.getWorkflow().getStatus());
        } catch (JsonProcessingException e) {
            log.error("Error is json parsing fields ");
            throw new Exception();
        }
    }

    public Optional<Product> fetch(String productId) {
        List<Product> product = jdbcTemplate.query(
                FIND_BY_ID,
                new Object[]{productId},
                (rs, rowNum) -> getProduct(rs));
        if (product.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(product.iterator().next());
    }

    public void update(Product product) throws Exception {
        try {
            jdbcTemplate.update(UPDATE_QUERY,
                    product.getSellerId(), product.getTitle(), product.getManufacturer(),
                    product.isLowQuantity(), product.isSoldOut(), product.isBackorder(), objectMapper.writeValueAsString(product.getMetafields()),
                    product.isRequiresShipping(), product.isVisible(),
                    product.getPublishedAt().get$date(), product.getCreatedAt().get$date(), product.getWorkflow().getStatus(), product.getProductId());
        } catch (JsonProcessingException e) {
            log.error("Error is json parsing fields ");
            throw new Exception();
        }
    }
}
