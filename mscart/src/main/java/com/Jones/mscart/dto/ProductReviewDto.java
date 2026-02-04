package com.Jones.mscart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductReviewDto {
    @NotNull(message = "Product ID is required")
    private Long product_id;
    @NotBlank(message = "Comment can't be blank")
    private String comment;
    @NotNull(message = "ratings is required")
    private Double ratings;

    public ProductReviewDto() {}

    public Long getProduct_id() { return product_id; }
    public void setProduct_id(Long product_id) { this.product_id = product_id; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Double getRatings() { return ratings; }
    public void setRatings(Double ratings) { this.ratings = ratings; }
}
