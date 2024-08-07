package com.ecommerce.springJwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.WishListDto;
import com.ecommerce.springJwt.service.WishListService;

@RestController
@RequestMapping("/user")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/wishList")
    public ResponseEntity<?> AddproductToWishList(@RequestBody WishListDto wishListDto) {
        WishListDto addedToWishList = wishListService.addProductToWishList(wishListDto);
        if (addedToWishList == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bieşeyler yanlış gitti");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedToWishList);
    }
}
