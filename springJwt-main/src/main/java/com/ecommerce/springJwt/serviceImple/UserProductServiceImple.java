package com.ecommerce.springJwt.serviceImple;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.ProductDetailDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.model.FAQ;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.model.Review;
import com.ecommerce.springJwt.repository.FAQRepository;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.repository.ReviewRepository;
import com.ecommerce.springJwt.service.UserProductService;

@Service
public class UserProductServiceImple implements UserProductService {

    private ProductRepo productRepo;
    private FAQRepository faqRepository;
    private ReviewRepository reviewRepository;

    public UserProductServiceImple(ProductRepo productRepo, FAQRepository faqRepository,
            ReviewRepository reviewRepository) {
        this.productRepo = productRepo;
        this.faqRepository = faqRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchAllProductByTitle(String title) {
        List<Product> products = productRepo.findAllByNameContaining(title);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public ProductDetailDto getProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);
            ProductDetailDto productDetailDto = new ProductDetailDto();
            productDetailDto.setProductDto(product.getDto());
            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFaqDto).collect(Collectors.toList()));
            productDetailDto
                    .setReviewDtoList(reviewList.stream().map(Review::getReviewDto).collect(Collectors.toList()));
            return productDetailDto;
        }
        return null;
    }

}
