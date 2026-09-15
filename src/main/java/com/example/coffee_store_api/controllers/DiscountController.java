package com.example.coffee_store_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.coffee_store_api.dto.DiscountRequestDto;
import com.example.coffee_store_api.dto.DiscountResponseDto;
import com.example.coffee_store_api.services.DiscountService;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public DiscountResponseDto createDiscount(@RequestBody DiscountRequestDto discountRequestDto,
            @AuthenticationPrincipal String userId) {
        return discountService.createDiscount(discountRequestDto, new ObjectId(userId));
    }

    @GetMapping("/{id}")
    public DiscountResponseDto getDiscountDetail(@PathVariable("id") String id) {
        return discountService.getDiscountById(new ObjectId(id));
    }

    @GetMapping("/dashboard")
    public Page<DiscountResponseDto> getDiscounts(
            @RequestParam(name = "search", required = false, defaultValue = "") String search, Pageable pageable) {
        return discountService.getDiscountsDashboard(pageable, search);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public DiscountResponseDto updateDiscount(@PathVariable("id") String id, @RequestBody DiscountRequestDto request) {
        return discountService.updateDiscount(new ObjectId(id), request);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public String deleteDiscount(@PathVariable("id") String id) {
        return discountService.deleteDiscount(new ObjectId(id));
    }

}
