package com.ecommerce.springJwt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecommerce.springJwt.dto.AddProductInDto;
import com.ecommerce.springJwt.dto.OrderDto;
import com.ecommerce.springJwt.dto.PlacedOrderDto;

public interface CartService {

    public ResponseEntity<?> addProductToCart(AddProductInDto addProductInDto);

    public OrderDto getCartByUserId(Long userId);

    public OrderDto increasedProductQuantity(AddProductInDto addProductInDto);

    public OrderDto decreasedProductQuantity(AddProductInDto addProductInDto);

    public OrderDto placedOrder(PlacedOrderDto placedOrderDto);

    public List<OrderDto> getMyPlacedOrder(Long UserId);
}
