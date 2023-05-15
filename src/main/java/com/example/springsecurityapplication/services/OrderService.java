package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.entities.OrderEntity;
import com.example.springsecurityapplication.entities.OrdersProductsEntity;
import com.example.springsecurityapplication.entities.ProductEntity;
import com.example.springsecurityapplication.entities.UserEntity;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.OrdersProductsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrdersProductsRepository ordersProductsRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, UserService userService, OrdersProductsRepository ordersProductsRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.ordersProductsRepository = ordersProductsRepository;
        this.cartService = cartService;
    }

    @Transactional
    public void save(int userId) {
        OrderEntity orderEntity = new OrderEntity();
        Optional<UserEntity> userEntity = userService.findById(userId);

        userEntity.ifPresent(orderEntity::setUserEntity);
        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setStatus("создан");

        orderRepository.save(orderEntity);

        List<ProductEntity> productEntities = new ArrayList<>(cartService.findAllByUserId(userId));

        for (ProductEntity productEntity : productEntities)
            ordersProductsRepository.save(new OrdersProductsEntity(orderEntity, productEntity));

        userEntity.ifPresent(cartService::clean);
    }

    public List<OrderEntity> findAllByUserId(int userId) {
        Optional<UserEntity> userEntity = userService.findById(userId);

        if (userEntity.isPresent()) return orderRepository.findAllByUserEntity(userEntity.get());

        return Collections.emptyList();
    }

    public List<OrderEntity> findAll(Integer search) {
        if (search != null)
            return orderRepository.findById(search).map(List::of).orElse(Collections.emptyList());

        return orderRepository.findAll();
    }

    public OrderEntity findById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity findByIdEndingWith(String id) {
        List<OrderEntity> allOrders = orderRepository.findAll();
        for (OrderEntity order : allOrders) {
            String orderId = order.getId() + "";
            if (orderId.endsWith(id)) {
                return order;
            }
        }
        return null;
    }

    @Transactional
    public void changeStatus(int id, String status) {
        orderRepository.findById(id).ifPresent(orderEntity -> {
            orderEntity.setStatus(status);
            orderRepository.save(orderEntity);
        });
    }
}
