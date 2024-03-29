package com.example.springsecurityapplication.dto.dtio;

import com.example.springsecurityapplication.entities.ProductEntity;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Paths;

public class ProductDTIO {
    public final static String PRODUCTS_IMAGES_PATH = System.getProperty("user.dir") + "/uploads/images/";
    private MultipartFile multipartFile;
    @Size(min = 4, max = 256, message = "Количество символов в названии должно варьироваться от 4-х до 256-ти.")
    private String title;
    @Positive(message = "Цена должна быть положительным числом.")
    private BigDecimal price;

    private String descr;

    public ProductDTIO() {

    }

    public ProductDTIO(MultipartFile multipartFile, String title, BigDecimal price, String descr) {
        this.multipartFile = multipartFile;
        this.title = title;
        this.price = price;
        this.descr = descr;
    }

    public static @NotNull ProductEntity convertToProductEntity(@NotNull ProductDTIO productDTIO) {
        String originalFileName = productDTIO.multipartFile.getOriginalFilename();

        return new ProductEntity(
                originalFileName,
                Paths.get(PRODUCTS_IMAGES_PATH, originalFileName).toString(),
                productDTIO.multipartFile.getContentType(),
                productDTIO.multipartFile.getSize(),
                productDTIO.title,
                productDTIO.price,
                productDTIO.descr
        );
    }

    public static @NotNull ProductEntity convertToProductEntity(@NotNull ProductDTIO productDTIO, int id) {
        String originalFileName = productDTIO.multipartFile.getOriginalFilename();

        return new ProductEntity(
                id,
                originalFileName,
                Paths.get(PRODUCTS_IMAGES_PATH, originalFileName).toString(),
                productDTIO.multipartFile.getContentType(),
                productDTIO.multipartFile.getSize(),
                productDTIO.title,
                productDTIO.price,
                productDTIO.descr
        );
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
