package com.example.coffee_store_api.models;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

public class InvoiceDetail {

    private ObjectId productId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private List<ObjectId> discountIds;
    private String productName;
    private String productDescription;
    private String productAdditionalInfo;
    private String productImage;
    private ObjectId productCategoryId;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;
    private ObjectId productCreatedBy;

    public InvoiceDetail() {
    }

    public InvoiceDetail(ObjectId productId, int quantity, double unitPrice, double totalPrice,
            List<ObjectId> discountIds,
            String productName, String productDescription, String productAdditionalInfo, String productImage,
            ObjectId productCategoryId, LocalDateTime productCreatedAt, LocalDateTime productUpdatedAt,
            ObjectId productCreatedBy) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.discountIds = discountIds;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAdditionalInfo = productAdditionalInfo;
        this.productImage = productImage;
        this.productCategoryId = productCategoryId;
        this.productCreatedAt = productCreatedAt;
        this.productUpdatedAt = productUpdatedAt;
        this.productCreatedBy = productCreatedBy;
    }

    public ObjectId getProductId() {
        return this.productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public List<ObjectId> getDiscountIds() {
        return this.discountIds;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public String getProductAdditionalInfo() {
        return this.productAdditionalInfo;
    }

    public String getProductImage() {
        return this.productImage;
    }

    public ObjectId getProductCategoryId() {
        return this.productCategoryId;
    }

    public LocalDateTime getProductCreatedAt() {
        return this.productCreatedAt;
    }

    public LocalDateTime getProductUpdatedAt() {
        return this.productUpdatedAt;
    }

    public ObjectId getProductCreatedBy() {
        return this.productCreatedBy;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDiscountIds(List<ObjectId> discountIds) {
        this.discountIds = discountIds;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductAdditionalInfo(String productAdditionalInfo) {
        this.productAdditionalInfo = productAdditionalInfo;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductCategoryId(ObjectId productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setProductCreatedAt(LocalDateTime productCreatedAt) {
        this.productCreatedAt = productCreatedAt;
    }

    public void setProductUpdatedAt(LocalDateTime productUpdatedAt) {
        this.productUpdatedAt = productUpdatedAt;
    }

    public void setProductCreatedBy(ObjectId productCreatedBy) {
        this.productCreatedBy = productCreatedBy;
    }
}
