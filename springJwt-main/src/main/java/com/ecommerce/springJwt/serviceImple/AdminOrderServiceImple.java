package com.ecommerce.springJwt.serviceImple;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.OrderDto;
import com.ecommerce.springJwt.enums.OrderStatus;
import com.ecommerce.springJwt.model.Order;
import com.ecommerce.springJwt.repository.OrderRepository;
import com.ecommerce.springJwt.service.AdminOrderService;

import io.jsonwebtoken.security.PublicJwk;

@Service
public class AdminOrderServiceImple implements AdminOrderService {
    private OrderRepository orderRepository;

    public AdminOrderServiceImple(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getAllPlacedOrder() {
        List<Order> orderList = orderRepository
                .findAllByOrderStatusIn(List.of(OrderStatus.BEKLEMEDE, OrderStatus.ONAYLANDI, OrderStatus.KARGOLANDI,
                        OrderStatus.TESLIM_EDILDI, OrderStatus.IPTAL_EDILDI));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto changeOrderStatus(Long orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (Objects.equals(status, "KARGOLANDI")) {
                order.setOrderStatus(OrderStatus.KARGOLANDI);

            } else if (Objects.equals(status, "ONAYLANDI")) {
                order.setOrderStatus(OrderStatus.ONAYLANDI);

            } else if (Objects.equals(status, "IPTAL_EDILDI")) {
                order.setOrderStatus(OrderStatus.IPTAL_EDILDI);

            } else {
                order.setOrderStatus(OrderStatus.TESLIM_EDILDI);
            }
            return orderRepository.save(order).getOrderDto();
        }
        return null;
    }

    @Override
    public List<OrderDto> getMyPlacedOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyPlacedOrder'");
    }

}
