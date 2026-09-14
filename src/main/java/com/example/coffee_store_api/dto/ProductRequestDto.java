package com.example.coffee_store_api.dto;

import org.bson.types.ObjectId;

public class ProductRequestDto {
    private String name;
    private double price;
    private double stock;
    private String description;
    private String additionalInfo;
    private ObjectId productCategoryId;
    private String image;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, double price, double stock, String description,
            String additionalInfo,
            ObjectId productCategoryId, String image) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.additionalInfo = additionalInfo;
        this.productCategoryId = productCategoryId;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public double getStock() {
        return this.stock;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public ObjectId getProductCategoryId() {
        return this.productCategoryId;
    }

    public String getImage() {
        return this.image;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setProductCategoryId(ObjectId productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
