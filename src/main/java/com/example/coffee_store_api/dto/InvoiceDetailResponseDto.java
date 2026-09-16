package com.example.coffee_store_api.dto;

import java.time.LocalDateTime;

public class InvoiceDetailResponseDto {

    private String productId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String discountId;
    private String productName;
    private String productDescription;
    private String productAdditionalInfo;
    private String productImage;
    private String productCategoryId;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productUpdatedAt;
    private String productCreatedBy;

    public InvoiceDetailResponseDto() {
    }

    public InvoiceDetailResponseDto(String productId, int quantity, double unitPrice, double totalPrice,
            String discountId,
            String productName, String productDescription, String productAdditionalInfo, String productImage,
            String productCategoryId, LocalDateTime productCreatedAt, LocalDateTime productUpdatedAt,
            String productCreatedBy) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.discountId = discountId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productAdditionalInfo = productAdditionalInfo;
        this.productImage = productImage;
        this.productCategoryId = productCategoryId;
        this.productCreatedAt = productCreatedAt;
        this.productUpdatedAt = productUpdatedAt;
        this.productCreatedBy = productCreatedBy;
    }

    public String getProductId() {
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

    public String getDiscountId() {
        return this.discountId;
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

    public String getProductCategoryId() {
        return this.productCategoryId;
    }

    public LocalDateTime getProductCreatedAt() {
        return this.productCreatedAt;
    }

    public LocalDateTime getProductUpdatedAt() {
        return this.productUpdatedAt;
    }

    public String getProductCreatedBy() {
        return this.productCreatedBy;
    }

    public void setProductId(String productId) {
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

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
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

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setProductCreatedAt(LocalDateTime productCreatedAt) {
        this.productCreatedAt = productCreatedAt;
    }

    public void setProductUpdatedAt(LocalDateTime productUpdatedAt) {
        this.productUpdatedAt = productUpdatedAt;
    }

    public void setProductCreatedBy(String productCreatedBy) {
        this.productCreatedBy = productCreatedBy;
    }

}
