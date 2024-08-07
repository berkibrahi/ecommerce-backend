package com.ecommerce.springJwt.serviceImple;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.WishListDto;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.model.User;
import com.ecommerce.springJwt.model.WishList;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.repository.UserRepository;
import com.ecommerce.springJwt.repository.WishListRepository;
import com.ecommerce.springJwt.service.WishListService;

@Service
public class WishListServiceImple implements WishListService {

    private final UserRepository userRepository;
    private final ProductRepo productRepo;
    private final WishListRepository wishListRepository;

    public WishListServiceImple(UserRepository userRepository, ProductRepo productRepo,
            WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.productRepo = productRepo;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public WishListDto addProductToWishList(WishListDto wishListDto) {
        Optional<Product> optionalProduct = productRepo.findById(wishListDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishListDto.getUserId());
        if (optionalProduct.isPresent() && optionalUser.isPresent()) {

            WishList wishList = new WishList();
            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());

            return wishListRepository.save(wishList).geWishListDto();
        }
        return null;

    }
}
