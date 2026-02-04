package com.Jones.mscart.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Entity
@Table(name = "product_images")
public class Productimage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Products product;

    // Constructors, getters, setters
    public Productimage() {}

    public Productimage(String url, Products product) {
        this.url = url;
        this.product = product;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }
}