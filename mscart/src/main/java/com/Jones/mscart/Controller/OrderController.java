package com.Jones.mscart.Controller;
import com.Jones.mscart.Service.OrderService;


import com.Jones.mscart.Entity.Order;
import com.Jones.mscart.Service.OrderService;
import com.Jones.mscart.dto.CreateOrderRequest;
import com.Jones.mscart.dto.OrderCreatedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest){
        OrderCreatedDTO orderCreatedDTO = orderService.createOrder(orderRequest);
        return  ResponseEntity.ok().body(orderCreatedDTO);
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getOrder(@PathVariable String referenceId){
        Order order = orderService.getOrder(referenceId);
        return ResponseEntity.ok().body(order);
    }
}
