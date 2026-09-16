package com.example.coffee_store_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee_store_api.dto.CancelInvoiceRequestDto;
import com.example.coffee_store_api.dto.InvoiceRequestDto;
import com.example.coffee_store_api.dto.InvoiceResponseDto;
import com.example.coffee_store_api.services.InvoiceService;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PreAuthorize("hasAnyRole('admin', 'collab')")
    @PostMapping
    public InvoiceResponseDto createInvoice(@RequestBody InvoiceRequestDto invoiceRequest,
            @AuthenticationPrincipal String userId) {

        return invoiceService.createInvoice(invoiceRequest, new ObjectId(userId));
    }

    @GetMapping("/{id}")
    public InvoiceResponseDto getInvoiceDetail(@PathVariable("id") String id) {
        return invoiceService.getInvoiceDetail(new ObjectId(id));
    }

    @GetMapping("/dashboard")
    public Page<InvoiceResponseDto> getListInvoice(
            @RequestParam(name = "search", required = false, defaultValue = "") String search, Pageable pageable) {
        return invoiceService.getInvoices(pageable, search);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public InvoiceResponseDto cancelInvoice(@PathVariable("id") String id,
            @RequestBody CancelInvoiceRequestDto request,
            @AuthenticationPrincipal String userId) {

        return invoiceService.cancelInvoice(new ObjectId(id), request.getCancelReason(), new ObjectId(userId));
    }

}
