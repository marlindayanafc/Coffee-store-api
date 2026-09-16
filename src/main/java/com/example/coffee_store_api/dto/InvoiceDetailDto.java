package com.example.coffee_store_api.dto;

import org.bson.types.ObjectId;

public class InvoiceDetailDto {
    private ObjectId productId;
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
