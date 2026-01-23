package com.Jones.mscart.seed;

import com.Jones.mscart.Entity.Products;
import com.Jones.mscart.Repositry.ProductRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeed implements CommandLineRunner  {
    @Autowired
    private ProductRepositry productRepositry;
    @Override
    public void run(String... args) throws Exception{
        if(productRepositry.count()==0){
            List<Products> demoProducts =List.of(
                    new Products(null, "Apple iPhone 15", 799.0, "smart phone", "Electronics", 4.8, "Amazon", 5),
                    new Products(null, "MacBook Air M2", 1099.0, "laptop", "Electronics", 4.9, "Apple Store", 3),
                    new Products(null, "Boat Rockers 450", 49.0, "headphones", "Accessories", 4.3, "Flipkart", 20),
                    new Products(null, "Samsung Galaxy S24", 749.0, "smart phone", "Electronics", 4.7, "Flipkart", 8),
                    new Products(null, "HP Pavilion 14", 699.0, "laptop", "Electronics", 4.5, "Amazon", 6),
                    new Products(null, "Sony WH-1000XM5", 349.0, "headphones", "Accessories", 4.9, "Croma", 4),
                    new Products(null, "iPad Air 5", 599.0, "tablet", "Electronics", 4.8, "Apple Store", 7),
                    new Products(null, "OnePlus Nord CE 3", 329.0, "smart phone", "Electronics", 4.6, "Amazon", 10),
                    new Products(null, "Logitech MX Master 3S", 99.0, "mouse", "Accessories", 4.7, "Reliance Digital", 15),
                    new Products(null, "Samsung 27-inch Monitor", 249.0, "monitor", "Electronics", 4.4, "Flipkart", 9)
            );

            productRepositry.saveAll(demoProducts);
            System.out.println("Seeded demo products!");
        }
        else{
            System.out.println("Products already added skipping seeded");
        }
    }
}
