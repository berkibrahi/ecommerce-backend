package com.ecommerce.springJwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.AddProductInDto;
import com.ecommerce.springJwt.dto.OrderDto;
import com.ecommerce.springJwt.dto.PlacedOrderDto;
import com.ecommerce.springJwt.service.CartService;

import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInDto addProductInDto) {

        return cartService.addProductToCart(addProductInDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long userId) {

        OrderDto orderDto = cartService.getCartByUserId(userId);

        return ResponseEntity.ok(orderDto);
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDto> increasedProductQuantity(@RequestBody AddProductInDto addProductInDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increasedProductQuantity(addProductInDto));
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDto> decreasedProductQuantity(@RequestBody AddProductInDto addProductInDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreasedProductQuantity(addProductInDto));
    }

    @PostMapping("/placedOrder")
    public ResponseEntity<?> placedOrder(@RequestBody PlacedOrderDto placedOrderDto) {
        if (placedOrderDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("kullanıcı bulunuamadı");
        } else {

            return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placedOrder(placedOrderDto));
        }
    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<java.util.List<OrderDto>> getMyPlacedOrder(@PathVariable Long userId) {

        return ResponseEntity.ok(cartService.getMyPlacedOrder(userId));
    }

}
