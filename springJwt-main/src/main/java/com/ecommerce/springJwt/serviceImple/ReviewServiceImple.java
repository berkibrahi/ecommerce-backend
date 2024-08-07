package com.ecommerce.springJwt.serviceImple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.OrderedProductResponseDto;
import com.ecommerce.springJwt.dto.ProductDto;
import com.ecommerce.springJwt.dto.ReviewDto;
import com.ecommerce.springJwt.model.CartItem;
import com.ecommerce.springJwt.model.Order;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.model.Review;
import com.ecommerce.springJwt.model.User;
import com.ecommerce.springJwt.repository.OrderRepository;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.repository.ReviewRepository;
import com.ecommerce.springJwt.repository.UserRepository;
import com.ecommerce.springJwt.service.ReviewService;

@Service
public class ReviewServiceImple implements ReviewService {

    private final OrderRepository orderRepository;
    private final ProductRepo productRepo;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImple(OrderRepository orderRepository, ProductRepo productRepo, UserRepository userRepository,
            ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.productRepo = productRepo;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public OrderedProductResponseDto getOrderedProductDetailsByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        OrderedProductResponseDto orderedProductResponseDto = new OrderedProductResponseDto();

        if (optionalOrder.isPresent()) {

            orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductDto> productDtoList = new ArrayList<>();
            for (CartItem cartItem : optionalOrder.get().getCartItems()) {
                ProductDto productDto = new ProductDto();

                productDto.setId(cartItem.getProduct().getId());
                productDto.setName(cartItem.getProduct().getName());
                productDto.setPrice(cartItem.getPrice());
                productDto.setQuantity(cartItem.getQuantity());
                productDto.setByteImg(cartItem.getProduct().getImg());

                productDtoList.add(productDto);
            }
            orderedProductResponseDto.setProductList(productDtoList);
        }
        return orderedProductResponseDto;

    }

    @Override
    public boolean giveReview(ReviewDto reviewDto) {
        Optional<Product> optionalProduct = productRepo.findById(reviewDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Review review = new Review();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getReturnedImg());

            reviewRepository.save(review);
            return true;
        }
        return false;
    }
}
