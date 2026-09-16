package com.example.coffee_store_api.dto;

import java.util.List;

import org.bson.types.ObjectId;

public class InvoiceRequestDto {
    private ObjectId userId;
    private String customerInfo;
    private List<InvoiceDetailDto> details;
    private double total;
    private double subtotal;
    private double discountTotal;

    public InvoiceRequestDto() {
    }

    public InvoiceRequestDto(ObjectId userId, String customerInfo, List<InvoiceDetailDto> details, double total,
            double subtotal,
            double discountTotal) {
        this.userId = userId;
        this.customerInfo = customerInfo;
        this.details = details;
        this.total = total;
        this.subtotal = subtotal;
        this.discountTotal = discountTotal;
    }

    public ObjectId getUserId() {
        return this.userId;
    }

    public String getCustomerInfo() {
        return this.customerInfo;
    }

    public List<InvoiceDetailDto> getDetails() {
        return this.details;
    }

    public double getTotal() {
        return this.total;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public double getDiscountTotal() {
        return this.discountTotal;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public void setDetails(List<InvoiceDetailDto> details) {
        this.details = details;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }
}
