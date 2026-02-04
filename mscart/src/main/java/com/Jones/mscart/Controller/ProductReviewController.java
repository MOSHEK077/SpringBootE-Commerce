package com.Jones.mscart.Controller;

import com.Jones.mscart.Service.ProductService;
import com.Jones.mscart.Entity.ProductReview;
import com.Jones.mscart.dto.ProductReviewDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/reviews")
public class ProductReviewController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductReview> addReview(@RequestBody @Valid ProductReviewDto reviewDto){
        ProductReview savedReview = productService.addReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
}
