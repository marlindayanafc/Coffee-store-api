package com.example.coffee_store_api.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("invoiceDetail")
public class InvoiceDetail {

    private ObjectId productId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public InvoiceDetail() {
    }

    public InvoiceDetail(ObjectId productId, int quantity, double unitPrice, double totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
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
}
