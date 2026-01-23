package com.Jones.mscart.Controller;

import java.util.List;
import java.util.Map;

import com.Jones.mscart.Entity.ProductReview;
import com.Jones.mscart.Entity.Products;
import com.Jones.mscart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class productController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Map<String,Object> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {

        return productService.getAllProducts(page, size);
    }
    @GetMapping("/{id}")
    public Products getProductById(
            @PathVariable Long id){
        return productService.getProductById(id);
    }


    @GetMapping("/search")
    public List<Products> searchProducts(@RequestParam(required = false) String category,@RequestParam(required = false) Double min_price,@RequestParam(required = false) Double max_price,@RequestParam(required = false) String keyword,@RequestParam(required = false)  Double ratings){

        return productService.searchProducts(category,min_price,max_price,keyword,ratings);

    }


}
