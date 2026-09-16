package com.example.coffee_store_api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.coffee_store_api.models.InvoiceDetail;

public class InvoiceResponseDto {

    private String id;
    private String userId;
    private String customerInfo;
    private List<InvoiceDetailResponseDto> details;
    private String statusId;
    private double total;
    private double subtotal;
    private double discountTotal;
    private String createdBy;
    private String cancelledBy;
    private String cancelReason;
    private LocalDateTime cancelledAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public InvoiceResponseDto() {
    }

    public InvoiceResponseDto(String id, String userId, String customerInfo, List<InvoiceDetailResponseDto> details,
            String statusId,
            double total, double subtotal, double discountTotal, String createdBy, String cancelledBy,
            String cancelReason, LocalDateTime cancelledAt, LocalDateTime createdAt, LocalDateTime updatedAt,
            LocalDateTime deletedAt) {
        this.id = id;
        this.userId = userId;
        this.customerInfo = customerInfo;
        this.details = details;
        this.statusId = statusId;
        this.total = total;
        this.subtotal = subtotal;
        this.discountTotal = discountTotal;
        this.createdBy = createdBy;
        this.cancelledBy = cancelledBy;
        this.cancelReason = cancelReason;
        this.cancelledAt = cancelledAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getCustomerInfo() {
        return this.customerInfo;
    }

    public List<InvoiceDetailResponseDto> getDetails() {
        return this.details;
    }

    public String getStatusId() {
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

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getCancelledBy() {
        return this.cancelledBy;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public LocalDateTime getCancelledAt() {
        return this.cancelledAt;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public void setDetails(List<InvoiceDetailResponseDto> details) {
        this.details = details;
    }

    public void setStatusId(String statusId) {
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

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
