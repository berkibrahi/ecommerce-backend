package com.ecommerce.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springJwt.model.FAQ;
import com.ecommerce.springJwt.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByProductId(Long productId);
}
