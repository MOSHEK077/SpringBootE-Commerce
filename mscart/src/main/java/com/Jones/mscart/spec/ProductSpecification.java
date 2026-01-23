package com.Jones.mscart.spec;

import com.Jones.mscart.Entity.Products;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Products> hasCategory(String category){
        return (category == null || category.trim().isEmpty())
                ? null
                : (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Products> priceBetween(Double min, Double max) {
        return (root, query, criteriaBuilder) -> {

            if (min == null && max == null) return null;

            if (min == null)
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);

            if (max == null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min);

            return criteriaBuilder.between(root.get("price"), min, max);
        };
    }

    public static Specification<Products> hasNameOrDescriptionLike(String keyword) {
        return (root, query, criteriaBuilder) -> {

            if (keyword == null || keyword.trim().isEmpty()) return null;

            return criteriaBuilder.or(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            "%" + keyword.toLowerCase() + "%"
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("description")),
                            "%" + keyword.toLowerCase() + "%"
                    )
            );
        };
    }

    public static Specification<Products> ratingGreaterThan(Double ratings) {
        return (root, query, criteriaBuilder) -> {
            if (ratings == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("ratings"), ratings);
        };
    }
}
