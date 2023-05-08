package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.entities.CartEntity;
import com.example.springsecurityapplication.entities.ProductEntity;
import com.example.springsecurityapplication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findAllByUserEntity(UserEntity userEntity);

    Optional<CartEntity> findByUserEntityAndProductEntity(UserEntity userEntity, ProductEntity productEntity);

    void deleteAllByUserEntity(UserEntity userEntity);
}
