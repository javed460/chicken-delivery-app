package com.chickendelivery.repository;

import com.chickendelivery.model.Order;
import com.chickendelivery.model.OrderStatus;
import com.chickendelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByUser(User user);
    List<Order> findByUserOrderByOrderDateDesc(User user);
}