package com.example.coffee_store_api.dto;

public class CancelInvoiceRequestDto {
    private String cancelReason;

    public CancelInvoiceRequestDto() {
    }

    public CancelInvoiceRequestDto(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
