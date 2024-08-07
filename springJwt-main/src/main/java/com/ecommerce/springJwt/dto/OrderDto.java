package com.ecommerce.springJwt.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecommerce.springJwt.enums.OrderStatus;
import com.ecommerce.springJwt.model.CartItem;
import com.ecommerce.springJwt.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class OrderDto {

    private Long id;

    private String orderDescription;

    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private Long totalAmount;

    private OrderStatus orderStatus;
    private Long discount;
    private UUID trackingId;

    private String userName;

    private Long discountedPrice;
    private List<CartItemDto> cartItems;
}
