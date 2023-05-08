package com.example.springsecurityapplication.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadsConfig implements WebMvcConfigurer {
//    @Value("${uploads.path}")
//    private String uploadsPath;
    private String uploadsPath = "C:\\Users\\Андрей\\Desktop\\Spring\\DietShopProject\\uploads\\";

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:///" + uploadsPath + "/images/");
    }
}
