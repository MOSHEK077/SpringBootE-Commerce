////package com.Jones.mscart.Entity;
////
////import jakarta.persistence.*;
////import jakarta.validation.constraints.NotBlank;
////import jakarta.validation.constraints.NotNull;
////import jakarta.validation.constraints.PositiveOrZero;
////
////import java.util.List;
////import java.util.stream.Collectors;
////
////@Entity
////@Table(name = "products")
////public class Products {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @Column(nullable = false)
////    @NotBlank(message = "Name field is required")
////    private String name;
////
////    @Column(nullable = false)
////    @NotNull(message = "Price field is required")
////    @PositiveOrZero(message = "Value must be zero or greater value")
////    private Double price;
////
////    @NotBlank(message = "Description field is required")
////    private String description;
////
////    private String category;
////    private Double ratings = 0.0;
////
////    @NotBlank(message = "Seller field is required")
////    private String seller;
////
////    @NotNull(message = "Stock field is required")
////    private Integer stock;
////
////    public String getCategory() {
////        return category;
////    }
////
////    public void setCategory(String category) {
////        this.category = category;
////    }
////
////    @NotNull(message = "numOfReviews field is required")
////    private Integer numOfReviews = 0;
////
////    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
////    @JoinColumn(name = "product_id")
////    private List<Productimage> images;
////
////    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
////    @JoinColumn(name = "product_id")
////    private List<ProductReview> reviews;
////
////    public Products() { super(); }
////
////    public Products(Long id, String name, Double price, String description, String category, Double ratings, String seller, Integer stock,List<String> images) {
////        this.id = id;
////        this.stock = stock;
////        this.price = price;
////        this.name = name;
////        this.description = description;
////        this.category = category;
////        this.ratings = ratings;
////        this.seller = seller;
////        this.images = images.stream().map(url -> new Productimage(url,this)).collect(Collectors.toList());
////    }
////
////    // getters / setters
////    public Long getId() { return id; }
////    public void setId(Long id) { this.id = id; }
////
////    public String getName() { return name; }
////    public void setName(String name) { this.name = name; }
////
////    public Double getPrice() { return price; }
////    public void setPrice(Double price) { this.price = price; }
////
////    public String getDescription() { return description; }
////    public void setDescription(String description) { this.description = description; }
////
////    public Double getRatings() { return ratings; }
////    public void setRatings(Double ratings) { this.ratings = ratings; }
////
////    public String getSeller() { return seller; }
////    public void setSeller(String seller) { this.seller = seller; }
////
////    public Integer getStock() { return stock; }
////    public void setStock(Integer stock) { this.stock = stock; }
////
////    public Integer getNumOfReviews() { return numOfReviews; }
////    public void setNumOfReviews(Integer numOfReviews) { this.numOfReviews = numOfReviews; }
////
////    public List<Productimage> getImages() { return images; }
////    public void setImages(List<Productimage> images) { this.images = images; }
////
////    public List<ProductReview> getReviews() { return reviews; }
////    public void setReviews(List<ProductReview> reviews) { this.reviews = reviews; }
////}
//
//
//
////-----------------------
//
//package com.Jones.mscart.Entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "product_images")
//public class Productimage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String url;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    @JsonBackReference
//    private Products product;
//
//    // 🔹 Mandatory no-arg constructor (JPA rule)
//    public Productimage() {
//    }
//
//    // 🔹 Useful constructor
//    public Productimage(String url, Products product) {
//        this.url = url;
//        this.product = product;
//    }
//
//    // 🔹 Full constructor (optional but clean)
//    public Productimage(Long id, String url, Products product) {
//        this.id = id;
//        this.url = url;
//        this.product = product;
//    }
//
//    // 🔹 Getters & Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public Products getProduct() {
//        return product;
//    }
//
//    public void setProduct(Products product) {
//        this.product = product;
//    }
//
//    public Productimage(Long id, Products product, String url) {
//        this.id = id;
//        this.product = product;
//        this.url = url;
//    }
//}
