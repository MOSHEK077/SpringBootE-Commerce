package com.Jones.mscart.Repositry;

import com.Jones.mscart.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepositry extends JpaRepository<Products,Long> , JpaSpecificationExecutor<Products> {
    //It helps to use the CRUD operations automatically
}
