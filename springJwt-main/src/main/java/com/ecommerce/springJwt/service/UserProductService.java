package com.ecommerce.springJwt.service;

import java.util.List;

import com.ecommerce.springJwt.dto.ProductDetailDto;
import com.ecommerce.springJwt.dto.ProductDto;

public interface UserProductService {

    public List<ProductDto> getAllProducts();

    public List<ProductDto> searchAllProductByTitle(String title);

    public ProductDetailDto getProductDetailById(Long productId);
}
