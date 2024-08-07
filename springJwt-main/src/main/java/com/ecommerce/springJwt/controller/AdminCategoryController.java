package com.ecommerce.springJwt.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.CategoryDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.model.Category;
import com.ecommerce.springJwt.service.CategorySevice;
import com.ecommerce.springJwt.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/admin")
public class AdminCategoryController {
    private CategorySevice categorySevice;

    public AdminCategoryController(CategorySevice categorySevice) {
        this.categorySevice = categorySevice;
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCatgory(@RequestBody CategoryDto categoryDto) {
        Category category = categorySevice.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

}
