package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.embeddable.OrdersProductsKey;
import com.example.springsecurityapplication.entities.OrdersProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersProductsRepository extends JpaRepository<OrdersProductsEntity, OrdersProductsKey> {

}
