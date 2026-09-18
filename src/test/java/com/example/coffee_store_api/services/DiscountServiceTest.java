package com.example.coffee_store_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.coffee_store_api.dto.DiscountRequestDto;
import com.example.coffee_store_api.dto.DiscountResponseDto;
import com.example.coffee_store_api.exceptions.InvalidDiscountDateException;
import com.example.coffee_store_api.models.Status;
import com.example.coffee_store_api.models.TypeDiscount;
import com.example.coffee_store_api.repositories.DiscountRepository;
import com.example.coffee_store_api.repositories.StatusRepository;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceTest {

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private DiscountService discountService;

    @Test
    public void shouldThrowWhenExpiredAtIsInThePast() {
        DiscountRequestDto request = new DiscountRequestDto("Promo vencida", 10, TypeDiscount.FIXED,
                new ObjectId(), null, null, LocalDateTime.now().minusDays(1));

        assertThrows(InvalidDiscountDateException.class,
                () -> discountService.createDiscount(request, new ObjectId()));
    }

    @Test
    public void shouldCreateDiscountWhenExpiredAtIsInTheFuture() {
        Status status = new Status("active");
        ReflectionTestUtils.setField(status, "id", new ObjectId());

        DiscountRequestDto request = new DiscountRequestDto("Promo vigente", 15, TypeDiscount.PERCENTAGE,
                new ObjectId(), null, null, LocalDateTime.now().plusDays(10));

        when(statusRepository.findByDescriptionIgnoreCase("active")).thenReturn(Optional.of(status));
        when(discountRepository.save(org.mockito.ArgumentMatchers.any())).thenAnswer(invocation -> {
            var discount = invocation.getArgument(0, com.example.coffee_store_api.models.Discount.class);
            ReflectionTestUtils.setField(discount, "id", new ObjectId());
            return discount;
        });

        DiscountResponseDto response = discountService.createDiscount(request, new ObjectId());

        assertEquals("Promo vigente", response.getDescription());
        assertEquals(15.0, response.getValue());
    }
}
