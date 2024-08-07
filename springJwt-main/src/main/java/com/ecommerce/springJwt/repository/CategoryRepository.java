package com.ecommerce.springJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.springJwt.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
