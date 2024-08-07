package com.ecommerce.springJwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.springJwt.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByOrderIdAndProductIdAndUserId(Long id, Long productId, Long userId);

}
