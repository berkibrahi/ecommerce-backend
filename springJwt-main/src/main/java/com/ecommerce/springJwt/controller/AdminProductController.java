package com.ecommerce.springJwt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.FAQDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.service.FAQservice;
import com.ecommerce.springJwt.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    private ProductService productService;

    public AdminProductController(ProductService productService, FAQservice faQservice) {
        this.productService = productService;
        this.faQservice = faQservice;
    }

    private FAQservice faQservice;

    @PostMapping("/saveproduct")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws IOException {
        ProductDto productDto1 = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name) {
        List<ProductDto> productDtos = productService.getAllProductByName(name);
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
            throws IOException {
        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> postFaq(@PathVariable Long productId, @RequestBody FAQDto faqDto) {
        FAQDto responseDto = faQservice.postFaq(productId, faqDto);
        if (responseDto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = productService.getProductById(productId);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
