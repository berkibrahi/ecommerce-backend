package com.ecommerce.springJwt.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderedProductResponseDto {

    private List<ProductDto> productList;

    private Long orderAmount;

}
