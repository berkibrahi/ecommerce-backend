package com.ecommerce.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springJwt.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContaining(String title);
}
