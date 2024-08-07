package com.ecommerce.springJwt.service;

import com.ecommerce.springJwt.dto.WishListDto;

/**
 * WishListService
 */
public interface WishListService {
    public WishListDto addProductToWishList(WishListDto wishListDto);
}