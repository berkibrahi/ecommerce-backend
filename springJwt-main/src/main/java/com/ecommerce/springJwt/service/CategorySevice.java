package com.ecommerce.springJwt.service;

import com.ecommerce.springJwt.dto.CategoryDto;
import com.ecommerce.springJwt.model.Category;

public interface CategorySevice {

    public Category createCategory(CategoryDto categoryDto);
}
