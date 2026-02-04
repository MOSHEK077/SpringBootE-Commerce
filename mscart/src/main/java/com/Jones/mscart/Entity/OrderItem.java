package com.Jones.mscart.Entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private Integer quantity;
    private String image;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Products products;

    public OrderItem() {
    }

    public OrderItem(String name, Integer quantity, String image, Double price, Products products) {
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }


}
