package com.ecommerce.springJwt.service;

import java.util.List;

import com.ecommerce.springJwt.dto.OrderDto;

public interface AdminOrderService {
    public List<OrderDto> getAllPlacedOrder();

    public OrderDto changeOrderStatus(Long orderId, String status);

    public List<OrderDto> getMyPlacedOrder();

}
