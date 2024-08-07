package com.ecommerce.springJwt.service;

import java.io.IOException;
import java.util.List;

import com.ecommerce.springJwt.dto.ProductDetailDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.model.Product;

public interface ProductService {

    public ProductDto addProduct(ProductDto productDto) throws IOException;

    public List<ProductDto> getAllProducts();

    public List<ProductDto> getAllProductByName(String name);

    public ProductDto updateProduct(Long id, ProductDto productDto) throws IOException;

    public void deleteProduct(Long id);

    public ProductDto getProductById(Long productId);

}
