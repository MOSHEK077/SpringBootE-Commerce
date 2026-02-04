package com.Jones.mscart.Service;


import com.Jones.mscart.Entity.Order;
import com.Jones.mscart.Entity.OrderItem;
import com.Jones.mscart.Entity.Products;
import com.Jones.mscart.Repositry.OrderRepositry;
import com.Jones.mscart.Repositry.ProductRepositry;
import com.Jones.mscart.dto.CreateOrderRequest;
import com.Jones.mscart.dto.OrderCreatedDTO;
import com.Jones.mscart.dto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepositry productRepositry;
    @Autowired
    private OrderRepositry orderRepositry;

    public OrderCreatedDTO createOrder(CreateOrderRequest orderRequest) {

        if (orderRequest.getOrderItemsDto() == null || orderRequest.getOrderItemsDto().isEmpty()) {
            throw new RuntimeException("Order items cannot be null or empty");
        }

        Order order = new Order();
        order.setStatus("PENDING");

        double totalItemAmount = 0;

        for (OrderItemDto itemDto : orderRequest.getOrderItemsDto()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setName(itemDto.getName());
            orderItem.setPrice(itemDto.getPrice());
            orderItem.setImage(itemDto.getImage());
            orderItem.setQuantity(itemDto.getQuantity());

            Products products = productRepositry.findById(itemDto.getProductid())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            orderItem.setProducts(products);

            totalItemAmount += itemDto.getPrice() * itemDto.getQuantity();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalItemAmount(totalItemAmount);

        double tax_amount = 10;
        double totalAmount = totalItemAmount + tax_amount;

        order.setTax_amount(tax_amount);
        order.setTotal_amount(totalAmount);
        String referenceId = UUID.randomUUID().toString();
        order.setReferenceId(referenceId);
        orderRepositry.save(order);
        return new OrderCreatedDTO(referenceId);
    }

    public Order getOrder( String referenceId){
        return orderRepositry.findByReferenceId(referenceId).orElseThrow(() -> new RuntimeException("No order found with reference Id! "));
    }


}


//Contribute to JVL Code;