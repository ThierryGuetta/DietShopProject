package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.entities.UserEntity;
import com.example.springsecurityapplication.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(@NotNull UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole("ROLE_USER");
        userEntity.setFirstName(userEntity.getFirstName());
        userEntity.setSecondName(userEntity.getSecondName());

        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void changeRole(int id, String role) {
        userRepository.findById(id).ifPresent(userEntity -> {
            userEntity.setRole(role);
            userRepository.save(userEntity);
        });
    }
}
