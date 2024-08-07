package com.ecommerce.springJwt.service;

import com.ecommerce.springJwt.dto.OrderedProductResponseDto;
import com.ecommerce.springJwt.dto.ReviewDto;

public interface ReviewService {
    public OrderedProductResponseDto getOrderedProductDetailsByOrderId(Long orderId);

    public boolean giveReview(ReviewDto reviewDto);
}
