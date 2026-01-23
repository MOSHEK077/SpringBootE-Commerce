package com.Jones.mscart.Service;

import com.Jones.mscart.Entity.Products;
import com.Jones.mscart.Repositry.ProductRepositry;
import com.Jones.mscart.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable; // correct import
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepositry productRepositry;

    public Map<String, Object> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // no cast, correct type
        Page<Products> products = productRepositry.findAll(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("Products", products.getContent());
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



}

