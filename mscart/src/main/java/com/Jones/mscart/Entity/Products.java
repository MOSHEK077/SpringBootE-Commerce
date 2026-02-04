package com.Jones.mscart.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name field is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Price field is required")
    @PositiveOrZero(message = "Value must be zero or greater value")
    private Double price;

    @NotBlank(message = "Description field is required")
    private String description;

    private String category;
    private Double ratings = 0.0;

    @NotBlank(message = "Seller field is required")
    private String seller;

    @NotNull(message = "Stock field is required")
    private Integer stock;

    @NotNull(message = "numOfReviews field is required")
    private Integer numOfReviews = 0;

    @NotNull(message = "Images field is required")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private List<Productimage> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<ProductReview> reviews = new ArrayList<>();

    // Updated constructor for JPA
    public Products() {
        super();
        this.images = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Helper method to add images
    public void addImage(String imageUrl) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        Productimage image = new Productimage(imageUrl, this);
        this.images.add(image);
    }

    // Helper method to add multiple images
    public void addImages(List<String> imageUrls) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        for (String url : imageUrls) {
            this.addImage(url);
        }
    }

    // Updated constructor
    public Products(Long id, String name, Double price, String description,
                    String category, Double ratings, String seller,
                    Integer stock, List<String> imageUrls) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.ratings = ratings;
        this.seller = seller;
        this.stock = stock;
        this.addImages(imageUrls);
    }

    // Getters and Setters (unchanged, but ensure they're there)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getRatings() { return ratings; }
    public void setRatings(Double ratings) { this.ratings = ratings; }

    public String getSeller() { return seller; }
    public void setSeller(String seller) { this.seller = seller; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Integer getNumOfReviews() { return numOfReviews; }
    public void setNumOfReviews(Integer numOfReviews) { this.numOfReviews = numOfReviews; }

    public List<Productimage> getImages() { return images; }
    public void setImages(List<Productimage> images) { this.images = images; }

    public List<ProductReview> getReviews() { return reviews; }
    public void setReviews(List<ProductReview> reviews) { this.reviews = reviews; }
}