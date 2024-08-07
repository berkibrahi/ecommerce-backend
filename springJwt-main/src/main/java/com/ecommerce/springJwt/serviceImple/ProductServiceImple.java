package com.ecommerce.springJwt.serviceImple;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.controller.ReviewController;
import com.ecommerce.springJwt.dto.ProductDetailDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.model.Category;
import com.ecommerce.springJwt.model.FAQ;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.model.Review;
import com.ecommerce.springJwt.repository.CategoryRepository;
import com.ecommerce.springJwt.repository.FAQRepository;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.repository.ReviewRepository;
import com.ecommerce.springJwt.service.ProductService;

@Service
public class ProductServiceImple implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepository categoryRepository;

    public ProductServiceImple(ProductRepo productRepo, CategoryRepository categoryRepository) {
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getByteImg());
        product.setQuantity(productDto.getQuantity());
        product.setDiscount(productDto.getDiscount());

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);
        return productRepo.save(product).getDto();

    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductByName(String name) {
        List<Product> products = productRepo.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) throws IOException {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImg(productDto.getByteImg());
        product.setQuantity(product.getQuantity());
        product.setDiscount(productDto.getDiscount());

        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow());
        return productRepo.save(product).getDto();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepo.delete(product);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getDto();
        }
        return null;
    }

}
