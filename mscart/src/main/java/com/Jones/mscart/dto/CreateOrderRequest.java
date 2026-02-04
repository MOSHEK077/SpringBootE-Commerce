package com.Jones.mscart.dto;

import com.Jones.mscart.Entity.OrderItem;

import java.util.List;

public class CreateOrderRequest {
    public List<OrderItemDto> getOrderItemsDto() {
        return orderItemsDto;
    }

    public void setOrderItemsDto(List<OrderItemDto> orderItemsDto) {
        this.orderItemsDto = orderItemsDto;
    }

    private List<OrderItemDto> orderItemsDto;
}
