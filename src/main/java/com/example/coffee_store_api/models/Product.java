package com.example.coffee_store_api.models;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private double price;
    private double stock;
    private ObjectId statusId;
    private String description;
    private String additionalInfo;
    private ObjectId productCategoryId;
    private String image;
    private ObjectId createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Product() {
    }

    public Product(String name, double price, double stock, ObjectId statusId, String description,
            String additionalInfo,
            ObjectId productCategoryId, String image, ObjectId createdBy) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.statusId = statusId;
        this.description = description;
        this.additionalInfo = additionalInfo;
        this.productCategoryId = productCategoryId;
        this.image = image;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public ObjectId getId() {
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

    public ObjectId getStatusId() {
        return this.statusId;
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

    public ObjectId getCreatedBy() {
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

    public void setStatusId(ObjectId statusId) {
        this.statusId = statusId;
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

    public void setCreatedBy(ObjectId createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
