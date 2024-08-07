package com.ecommerce.springJwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.OrderedProductResponseDto;
import com.ecommerce.springJwt.dto.ReviewDto;
import com.ecommerce.springJwt.service.ReviewService;

@RestController
@RequestMapping("/user")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/orderedProduct/{orderId}")
    public ResponseEntity<OrderedProductResponseDto> getOrderedProductDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(reviewService.getOrderedProductDetailsByOrderId(orderId));
    }

    @PostMapping("review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto) {
        boolean success = reviewService.giveReview(reviewDto);
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("birşeyler ters gitti");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
