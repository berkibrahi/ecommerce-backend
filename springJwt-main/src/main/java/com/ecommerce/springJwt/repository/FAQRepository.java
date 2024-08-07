package com.ecommerce.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springJwt.model.FAQ;

public interface FAQRepository extends JpaRepository<FAQ, Long> {

    public List<FAQ> findAllByProductId(Long productId);
}
