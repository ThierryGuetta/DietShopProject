package com.example.springsecurityapplication.dto.dtoo;

import java.math.BigDecimal;

public class ProductDTOO {
    private int id;
    private String fileName;
    private String title;
    private BigDecimal price;
    private String descr;

    public ProductDTOO() {

    }

    public ProductDTOO(int id, String fileName, String title, BigDecimal price, String descr) {
        this.id = id;
        this.fileName = fileName;
        this.title = title;
        this.price = price;
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
