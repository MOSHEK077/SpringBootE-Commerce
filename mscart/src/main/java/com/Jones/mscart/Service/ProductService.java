package com.Jones.mscart.Service;

import com.Jones.mscart.Entity.ProductReview;
import com.Jones.mscart.Entity.Productimage;
import com.Jones.mscart.Entity.Products;
import com.Jones.mscart.Repositry.ProductRepositry;
import com.Jones.mscart.Repositry.ProductReviewRepository;
import com.Jones.mscart.dto.ProductDTO;
import com.Jones.mscart.dto.ProductImageDto;
import com.Jones.mscart.dto.ProductReviewDto;
import com.Jones.mscart.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepositry productRepositry;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public Map<String, Object> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Products> products = productRepositry.findAll(pageable);
        List<ProductDTO> productDTOS = products.stream().map(this::convertToDTO).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("Products", productDTOS);
        response.put("totalProducts", products.getTotalElements());
        return response;
    }

    public Products getProductById(Long id) {
        return productRepositry.findById(id).orElseThrow(() -> new RuntimeException("Product not found with this id!"));
    }

    public List<Products> searchProducts(
            String category,
            Double min_price,
            Double max_price,
            String keyword,
            Double ratings
    ) {
        Specification<Products> spec = null;

        if (category != null && !category.trim().isEmpty()) {
            spec = Specification.where(ProductSpecification.hasCategory(category));
        }

        if (min_price != null || max_price != null) {
            spec = (spec == null)
                    ? Specification.where(ProductSpecification.priceBetween(min_price, max_price))
                    : spec.and(ProductSpecification.priceBetween(min_price, max_price));
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            spec = (spec == null)
                    ? Specification.where(ProductSpecification.hasNameOrDescriptionLike(keyword))
                    : spec.and(ProductSpecification.hasNameOrDescriptionLike(keyword));
        }

        if (ratings != null) {
            spec = (spec == null)
                    ? Specification.where(ProductSpecification.ratingGreaterThan(ratings))
                    : spec.and(ProductSpecification.ratingGreaterThan(ratings));
        }

        return (spec == null)
                ? productRepositry.findAll()
                : productRepositry.findAll(spec);
    }

    public ProductReview addReview(ProductReviewDto reviewDto) {

        if (reviewDto == null) {
            throw new IllegalArgumentException("Request body is missing");
        }

        if (reviewDto.getProduct_id() == null) {
            throw new IllegalArgumentException("product_id is required");
        }

        Products product = productRepositry.findById(reviewDto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + reviewDto.getProduct_id()));

        Double ratingsDouble = reviewDto.getRatings();
        if (ratingsDouble == null) {
            throw new IllegalArgumentException("ratings is required");
        }

        int rating = ratingsDouble.intValue(); // if you want rounding: (int)Math.round(ratingsDouble)
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("ratings must be between 1 and 5");
        }

        ProductReview review = new ProductReview();
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(reviewDto.getComment());

        ProductReview saved = productReviewRepository.save(review);

        // Update product aggregates: numOfReviews and average ratings
        Integer currentNum = product.getNumOfReviews() == null ? 0 : product.getNumOfReviews();
        Double currentAvg = product.getRatings() == null ? 0.0 : product.getRatings();

        int newNum = currentNum + 1;
        double newAvg = ((currentAvg * currentNum) + rating) / (double) newNum;

        product.setNumOfReviews(newNum);
        product.setRatings(newAvg);

        productRepositry.save(product);

        return saved;
    }

    public ProductDTO convertToDTO(Products products){
        ProductDTO dto = new ProductDTO();
        dto.setId(products.getId());
        dto.setName(products.getName());
        dto.setPrice(products.getPrice()); // ADDED THIS LINE - CRITICAL!
        dto.setDescription(products.getDescription());
        dto.setCategory(products.getCategory()); // ADDED THIS LINE
        dto.setRatings(products.getRatings());
        dto.setSeller(products.getSeller());
        dto.setStock(products.getStock());
        dto.setNumOfReviews(products.getNumOfReviews());

        // Convert entity reviews -> ProductReviewDto
        List<ProductReviewDto> reviewDtos = Optional.ofNullable(products.getReviews())
                .orElse(Collections.emptyList())
                .stream()
                .map(productReview -> {
                    ProductReviewDto rd = new ProductReviewDto();
                    rd.setProduct_id(products.getId());
                    rd.setComment(productReview.getComment());
                    rd.setRatings(productReview.getRating() != null ? productReview.getRating().doubleValue() : 0.0);
                    return rd;
                }).collect(Collectors.toList());

        dto.setReviews(reviewDtos);

        // Convert entity images -> ProductImageDto
        List<ProductImageDto> productImageDtos;

        // Check if images is null and handle gracefully
        if (products.getImages() == null) {
            productImageDtos = new ArrayList<>();
        } else {
            productImageDtos = products.getImages().stream()
                    .map(productimage -> {
                        ProductImageDto productImageDto = new ProductImageDto();
                        productImageDto.setUrl(productimage.getUrl());
                        return productImageDto;
                    }).collect(Collectors.toList());
        }

        dto.setImages(productImageDtos);

        return dto;
    }

    // Optional: Add a method to get ProductDTO by ID
    public ProductDTO getProductDTOById(Long id) {
        Products product = getProductById(id);
        return convertToDTO(product);
    }

    // Optional: Add a method to update product with images
    public Products addImagesToProduct(Long productId, List<String> imageUrls) {
        Products product = getProductById(productId);

        if (product.getImages() == null) {
            product.setImages(new ArrayList<>());
        }

        // Add new images
        for (String url : imageUrls) {
            Productimage image = new Productimage();
            image.setUrl(url);
            image.setProduct(product);
            product.getImages().add(image);
        }

        return productRepositry.save(product);
    }
}