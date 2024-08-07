package com.ecommerce.springJwt.serviceImple;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.CategoryDto;
import com.ecommerce.springJwt.model.Category;
import com.ecommerce.springJwt.repository.CategoryRepository;
import com.ecommerce.springJwt.service.CategorySevice;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service

public class CategoryServiceImple implements CategorySevice {

    public CategoryServiceImple(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }
}
