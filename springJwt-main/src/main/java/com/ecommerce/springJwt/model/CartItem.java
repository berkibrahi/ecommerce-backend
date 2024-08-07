package com.ecommerce.springJwt.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecommerce.springJwt.dto.CartItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long price;
    private Long quantity;
    private Long discount;
    private Long discountedPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")

    private Order order;

    public CartItemDto getCartItemDto() {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(id);
        cartItemDto.setPrice(price);
        cartItemDto.setProductId(product.getId());
        cartItemDto.setQuantity(quantity);
        cartItemDto.setUserId(user.getId());
        cartItemDto.setProductName(product.getName());
        cartItemDto.setReturnedImg(product.getImg());
        cartItemDto.setOrderId(order.getId());
        return cartItemDto;
    }

}
