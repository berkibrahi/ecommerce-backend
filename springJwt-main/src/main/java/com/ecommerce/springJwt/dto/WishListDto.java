package com.ecommerce.springJwt.dto;

import lombok.Data;

@Data
public class WishListDto {

    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private String productDescription;
    private String img;
    private Long price;
    private Long discount;

}
