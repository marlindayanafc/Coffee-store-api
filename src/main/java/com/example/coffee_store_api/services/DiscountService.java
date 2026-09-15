package com.example.coffee_store_api.services;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.coffee_store_api.dto.DiscountRequestDto;
import com.example.coffee_store_api.dto.DiscountResponseDto;
import com.example.coffee_store_api.dto.ProductResponseDto;
import com.example.coffee_store_api.exceptions.InvalidDiscountDateException;
import com.example.coffee_store_api.models.Discount;
import com.example.coffee_store_api.models.Status;
import com.example.coffee_store_api.repositories.DiscountRepository;
import com.example.coffee_store_api.repositories.StatusRepository;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final StatusRepository statusRepository;

    public DiscountService(DiscountRepository discountRepository, StatusRepository statusRepository) {
        this.discountRepository = discountRepository;
        this.statusRepository = statusRepository;
    }

    public DiscountResponseDto toDiscountDto(Discount discount) {
        return new DiscountResponseDto(discount.getId().toHexString(), discount.getDescription(),
                discount.getValue(),
                discount.getType(), discount.getProductId().toHexString(),
                discount.getStatusId().toHexString(),
                discount.getCreatedBy().toHexString(), discount.getExpiredAt(), discount.getCreatedAt(),
                discount.getUpdatedAt(), discount.getDeletedAt());
    }

    public DiscountResponseDto createDiscount(DiscountRequestDto request, ObjectId userId) {

        if (request.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new InvalidDiscountDateException("The expiredDate must be greates than currentDate");
        }

        Status status = statusRepository.findByDescriptionIgnoreCase("active").orElseThrow();

        Discount discount = new Discount(request.getDescription(), request.getValue(), request.getType(),
                request.getProductId(), status.getId(), userId, request.getExpiredAt());

        Discount discountSaved = discountRepository.save(discount);

        return new DiscountResponseDto(discountSaved.getId().toHexString(), discountSaved.getDescription(),
                discountSaved.getValue(),
                discountSaved.getType(), discountSaved.getProductId().toHexString(),
                status.getId().toHexString(),
                discountSaved.getCreatedBy().toHexString(), discountSaved.getExpiredAt(), discountSaved.getCreatedAt(),
                discountSaved.getUpdatedAt(), discountSaved.getDeletedAt());
    }

    public DiscountResponseDto updateDiscount(ObjectId id, DiscountRequestDto request) {
        Discount discount = discountRepository.findById(id).orElseThrow();

        discount.setDescription(request.getDescription());
        discount.setValue(request.getValue());
        discount.setType(request.getType());
        discount.setProductId(request.getProductId());
        discount.setExpiredAt(request.getExpiredAt());
        discount.setUpdatedAt(LocalDateTime.now());

        discountRepository.save(discount);

        return new DiscountResponseDto(discount.getId().toHexString(), discount.getDescription(),
                discount.getValue(),
                discount.getType(), discount.getProductId().toHexString(),
                discount.getStatusId().toHexString(),
                discount.getCreatedBy().toHexString(), discount.getExpiredAt(), discount.getCreatedAt(),
                discount.getUpdatedAt(), discount.getDeletedAt());
    }

    public DiscountResponseDto getDiscountById(ObjectId id) {
        Discount discount = discountRepository.findById(id).orElseThrow();

        return new DiscountResponseDto(discount.getId().toHexString(), discount.getDescription(),
                discount.getValue(),
                discount.getType(), discount.getProductId().toHexString(),
                discount.getStatusId().toHexString(),
                discount.getCreatedBy().toHexString(), discount.getExpiredAt(), discount.getCreatedAt(),
                discount.getUpdatedAt(), discount.getDeletedAt());
    }

    public Page<DiscountResponseDto> getDiscountsDashboard(Pageable pageable, String search) {
        return discountRepository.findByDeletedAtIsNullAndDescriptionContainingIgnoreCase(search, pageable)
                .map(this::toDiscountDto);
    }

    public String deleteDiscount(ObjectId id) {
        Discount discount = discountRepository.findById(id).orElseThrow();

        discount.setDeletedAt(LocalDateTime.now());

        discountRepository.save(discount);

        return "Discount deleted succesfully";
    }

}
