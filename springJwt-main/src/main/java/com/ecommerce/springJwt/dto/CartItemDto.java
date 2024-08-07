package com.ecommerce.springJwt.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long price;
    private Long quantity;
    private Long productId;
    private Long orderId;
    private String productName;
    private String returnedImg;
    private Long userId;
    private Long discount;
    private Long discountedPrice;

}
