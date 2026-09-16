package com.example.coffee_store_api.dto;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InvoiceDetailDto {
    @NotNull
    private ObjectId productId;
    @Positive
    private int quantity;

    public InvoiceDetailDto() {
    }

    public InvoiceDetailDto(ObjectId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;

    }

    public ObjectId getProductId() {
        return this.productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
