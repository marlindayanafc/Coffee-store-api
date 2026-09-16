package com.example.coffee_store_api.dto;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import com.example.coffee_store_api.models.TypeDiscount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DiscountRequestDto {
    @NotBlank
    private String description;
    @Positive
    private double value;
    @NotNull
    private TypeDiscount type;
    @NotNull
    private ObjectId productId;
    private ObjectId statusId;
    private ObjectId createdBy;
    @NotNull
    private LocalDateTime expiredAt;

    public DiscountRequestDto() {
    }

    public DiscountRequestDto(String description, double value, TypeDiscount type, ObjectId productId,
            ObjectId statusId,
            ObjectId createdBy, LocalDateTime expiredAt) {
        this.description = description;
        this.value = value;
        this.type = type;
        this.productId = productId;
        this.statusId = statusId;
        this.createdBy = createdBy;
        this.expiredAt = expiredAt;
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

    public ObjectId getProductId() {
        return this.productId;
    }

    public ObjectId getStatusId() {
        return this.statusId;
    }

    public ObjectId getCreatedBy() {
        return this.createdBy;
    }

    public LocalDateTime getExpiredAt() {
        return this.expiredAt;
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

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public void setStatusId(ObjectId statusId) {
        this.statusId = statusId;
    }

    public void setCreatedBy(ObjectId createdBy) {
        this.createdBy = createdBy;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

}
