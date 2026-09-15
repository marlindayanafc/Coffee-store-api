package com.example.coffee_store_api.dto;

import java.time.LocalDateTime;

import com.example.coffee_store_api.models.TypeDiscount;

public class DiscountResponseDto {
    private String id;
    private String description;
    private double value;
    private TypeDiscount type;
    private String productId;
    private String statusId;
    private String createdBy;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public DiscountResponseDto() {
    }

    public DiscountResponseDto(String id, String description, double value, TypeDiscount type, String productId,
            String statusId,
            String createdBy, LocalDateTime expiredAt, LocalDateTime createdAt, LocalDateTime updatedAt,
            LocalDateTime deletedAt) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.type = type;
        this.productId = productId;
        this.statusId = statusId;
        this.createdBy = createdBy;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public double getValue() {
        return this.value;
    }

    public TypeDiscount getType() {
        return this.type;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getStatusId() {
        return this.statusId;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public LocalDateTime getExpiredAt() {
        return this.expiredAt;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setType(TypeDiscount type) {
        this.type = type;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
