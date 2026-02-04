package com.Jones.mscart.Repositry;

import com.Jones.mscart.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepositry extends JpaRepository<Order,Long> {

    Optional<Order> findByReferenceId(String referenceId);


}
