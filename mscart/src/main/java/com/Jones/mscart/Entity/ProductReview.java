package com.Jones.mscart.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product_reviews")
public class ProductReview {

    public ProductReview() {}

    public ProductReview(Long id, Integer rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String comment;

    // FOREIGN KEY to products.id — use a different column than 'id'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    //@JsonIgnore

    private Products product;

    // constructors, getters, setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }
}
