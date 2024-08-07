package com.ecommerce.springJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.springJwt.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}
