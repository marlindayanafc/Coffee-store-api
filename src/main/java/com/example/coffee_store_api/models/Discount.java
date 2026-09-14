package com.example.coffee_store_api.models;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("discounts")
public class Discount {

    @Id
    private ObjectId id;
    private String description;
    private double value;
    private String type; // crear el enum
    private ObjectId productId;
    private ObjectId statusId;
    private ObjectId createdBy;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Discount() {
    }

    public Discount(String description, double value, String type, ObjectId productId, ObjectId statusId,
            ObjectId createdBy, LocalDateTime expiredAt) {
        this.description = description;
        this.value = value;
        this.type = type;
        this.productId = productId;
        this.statusId = statusId;
        this.createdBy = createdBy;
        this.expiredAt = expiredAt;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public ObjectId getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public double getValue() {
        return this.value;
    }

    public String getType() {
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

    public void setType(String type) {
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

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
