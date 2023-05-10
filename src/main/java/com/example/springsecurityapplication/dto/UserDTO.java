package com.example.springsecurityapplication.dto;

import com.example.springsecurityapplication.entities.UserEntity;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class UserDTO {
    @Size(min = 4, max = 256, message = "Имя пользователя должно содержать не менее 4-х символов.")
    private String username;
    @Size(min = 7, max = 32, message = "Пароль должен состоять как минимум из 7-ми символов.")
    private String password;
    private String confirmedPassword;
    private String first_name;
    private String second_name;

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull UserEntity convertToUserEntity(@NotNull UserDTO userDTO) {
        return new UserEntity(userDTO.username, userDTO.password, userDTO.first_name, userDTO.second_name);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }
}
