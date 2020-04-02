package com.unistore.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class Product {
    @NotBlank
    private String productId;
    private String sellerId;
    private String title;
    private String manufacturer;
    @JsonProperty("isLowQuantity")
    private boolean isLowQuantity;
    @JsonProperty("isSoldOut")
    private boolean isSoldOut;
    @JsonProperty("isBackorder")
    private boolean isBackorder;
    @Builder.Default
    ArrayList<MetaField> metafields = new ArrayList<>();
    private boolean requiresShipping;
    private boolean isVisible;
    PublishedAt publishedAt;
    UpdatedAt updatedAt;
    CreatedAt createdAt;
    Workflow workflow;

}

