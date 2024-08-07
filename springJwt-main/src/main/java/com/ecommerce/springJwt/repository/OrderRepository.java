package com.ecommerce.springJwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.springJwt.enums.OrderStatus;
import com.ecommerce.springJwt.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findAllByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatusList);

}
