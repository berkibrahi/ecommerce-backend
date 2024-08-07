package com.ecommerce.springJwt.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecommerce.springJwt.dto.WishListDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Product product;

    public WishListDto geWishListDto() {
        WishListDto wishListDto = new WishListDto();
        wishListDto.setId(id);
        wishListDto.setImg(product.getImg());
        wishListDto.setPrice(product.getPrice());
        wishListDto.setProductId(product.getId());
        wishListDto.setProductName(product.getName());
        wishListDto.setProductDescription(product.getDescription());
        wishListDto.setUserId(user.getId());
        wishListDto.setDiscount(product.getDiscount());
        return wishListDto;

    }
}
