package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.entities.OrderEntity;
import com.example.springsecurityapplication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByUserEntity(UserEntity userEntity);
}
