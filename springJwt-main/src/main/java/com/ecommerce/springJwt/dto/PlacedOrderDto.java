package com.ecommerce.springJwt.dto;

import lombok.Data;

@Data
public class PlacedOrderDto {
    private Long userId;

    private String address;

    private String orderDescription;

}