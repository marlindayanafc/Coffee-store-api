package com.example.coffee_store_api.dto;

import java.time.LocalDateTime;

public class ProductResponseDto {
    private String id;
    private String name;
    private double price;
    private double stock;
    private String statusId;
    private String description;
    private String additionalInfo;
    private String productCategoryId;
    private String image;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public ProductResponseDto() {
    }

    public ProductResponseDto(String id, String name, double price, double stock, String statusId,
            String description, String additionalInfo, String productCategoryId, String image, String createdBy,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.statusId = statusId;
        this.description = description;
        this.additionalInfo = additionalInfo;
        this.productCategoryId = productCategoryId;
        this.image = image;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return this.id;
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

    public String getStatusId() {
        return this.statusId;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public String getProductCategoryId() {
        return this.productCategoryId;
    }

    public String getImage() {
        return this.image;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }
}
