package com.example.coffee_store_api.models;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("invoices")
public class Invoice {

    @Id
    private ObjectId id;
    private ObjectId userId;
    private List<InvoiceDetail> details;
    private ObjectId statusId;
    private double total;
    private double subtotal;
    private double discountTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Invoice() {
    }

    public Invoice(ObjectId userId, List<InvoiceDetail> details, ObjectId statusId, double total, double subtotal,
            double discountTotal) {
        this.userId = userId;
        this.details = details;
        this.statusId = statusId;
        this.total = total;
        this.subtotal = subtotal;
        this.discountTotal = discountTotal;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public ObjectId getId() {
        return this.id;
    }

    public ObjectId getUserId() {
        return this.userId;
    }

    public List<InvoiceDetail> getDetails() {
        return this.details;
    }

    public ObjectId getStatusId() {
        return this.statusId;
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

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }

    public void setStatusId(ObjectId statusId) {
        this.statusId = statusId;
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

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
