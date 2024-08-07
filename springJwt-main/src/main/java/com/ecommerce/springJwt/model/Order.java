package com.ecommerce.springJwt.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecommerce.springJwt.dto.OrderDto;
import com.ecommerce.springJwt.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String orderDescription;

    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private Long totalAmount;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private Long discount;
    private Long discountedPrice;
    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItem> cartItems;

    public OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setAddress(address);
        orderDto.setDate(date);
        orderDto.setTrackingId(trackingId);
        orderDto.setAmount(amount);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setUserName(user.getUsername());

        return orderDto;
    }

}
