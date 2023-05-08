package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.entities.UserEntity;
import com.example.springsecurityapplication.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElse(null);

        if (userEntity == null)
            throw new UsernameNotFoundException("Пользователь с таким именем не найден.");

        return new com.example.springsecurityapplication.security.UserDetails(userEntity);
    }
}
