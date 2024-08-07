package com.ecommerce.springJwt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.ProductDetailDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.service.ProductService;
import com.ecommerce.springJwt.service.UserProductService;

@RestController
@RequestMapping("/user")
public class UserProductController {

    private UserProductService userProductService;

    public UserProductController(UserProductService userProductService) {
        this.userProductService = userProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = userProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<List<ProductDto>> searchAllProductByTitle(@PathVariable String name) {
        List<ProductDto> productDtos = userProductService.searchAllProductByTitle(name);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/productDetail/{ProductId}")
    public ResponseEntity<?> getProductDeailById(@PathVariable Long ProductId) {
        ProductDetailDto productDetailDto = userProductService.getProductDetailById(ProductId);
        if (productDetailDto == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(productDetailDto);

    }
}
