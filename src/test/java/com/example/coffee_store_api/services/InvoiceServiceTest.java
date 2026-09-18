package com.example.coffee_store_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.coffee_store_api.dto.InvoiceDetailDto;
import com.example.coffee_store_api.dto.InvoiceRequestDto;
import com.example.coffee_store_api.dto.InvoiceResponseDto;
import com.example.coffee_store_api.exceptions.InsufficientStockException;
import com.example.coffee_store_api.exceptions.InvalidCustomerDataException;
import com.example.coffee_store_api.exceptions.PriceMismatchException;
import com.example.coffee_store_api.models.Discount;
import com.example.coffee_store_api.models.Invoice;
import com.example.coffee_store_api.models.Product;
import com.example.coffee_store_api.models.Status;
import com.example.coffee_store_api.models.TypeDiscount;
import com.example.coffee_store_api.repositories.DiscountRepository;
import com.example.coffee_store_api.repositories.InvoiceRepository;
import com.example.coffee_store_api.repositories.ProductRepository;
import com.example.coffee_store_api.repositories.StatusRepository;
import com.example.coffee_store_api.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    private Product buildProduct(ObjectId id, double price, double stock) {
        Product product = new Product("Café Colombia 500g", price, stock, new ObjectId(),
                "Café en grano, tueste medio", "Notas a chocolate", new ObjectId(), "imagen.jpg", new ObjectId());
        ReflectionTestUtils.setField(product, "id", id);
        return product;
    }

    private Status buildStatus(String description) {
        Status status = new Status(description);
        ReflectionTestUtils.setField(status, "id", new ObjectId());
        return status;
    }

    private void mockActiveStatus() {
        when(statusRepository.findByDescriptionIgnoreCase("active")).thenReturn(Optional.of(buildStatus("active")));
    }

    private void mockInvoiceSave() {
        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> {
            Invoice invoice = invocation.getArgument(0);
            ReflectionTestUtils.setField(invoice, "id", new ObjectId());
            return invoice;
        });
    }

    @Test
    public void shouldCreateInvoiceWithoutDiscount() {
        ObjectId productId = new ObjectId();
        Product product = buildProduct(productId, 25000, 50);

        InvoiceRequestDto request = new InvoiceRequestDto(null, "Cliente de prueba",
                List.of(new InvoiceDetailDto(productId, 2)), 50000, 0, 0);

        when(productRepository.findAllById(any())).thenReturn(List.of(product));
        when(discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(any(), any()))
                .thenReturn(List.of());
        mockActiveStatus();
        when(productRepository.save(any())).thenReturn(product);
        mockInvoiceSave();

        InvoiceResponseDto response = invoiceService.createInvoice(request, new ObjectId());

        assertEquals(50000, response.getTotal());
        assertEquals(50000, response.getSubtotal());
        assertEquals(0, response.getDiscountTotal());
    }

    @Test
    public void shouldCreateInvoiceWithFixedDiscount() {
        ObjectId productId = new ObjectId();
        Product product = buildProduct(productId, 18000, 50);

        Discount discount = new Discount("Descuento fijo", 3550, TypeDiscount.FIXED, productId, new ObjectId(),
                new ObjectId(), LocalDateTime.now().plusDays(5));
        ReflectionTestUtils.setField(discount, "id", new ObjectId());

        InvoiceRequestDto request = new InvoiceRequestDto(null, "Cliente de prueba",
                List.of(new InvoiceDetailDto(productId, 12)), 173400, 0, 0);

        when(productRepository.findAllById(any())).thenReturn(List.of(product));
        when(discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(any(), any()))
                .thenReturn(List.of(discount));
        mockActiveStatus();
        when(productRepository.save(any())).thenReturn(product);
        mockInvoiceSave();

        InvoiceResponseDto response = invoiceService.createInvoice(request, new ObjectId());

        assertEquals(216000, response.getSubtotal());
        assertEquals(42600, response.getDiscountTotal());
        assertEquals(173400, response.getTotal());
    }

    @Test
    public void shouldCreateInvoiceWithPercentageDiscount() {
        ObjectId productId = new ObjectId();
        Product product = buildProduct(productId, 25000, 50);

        Discount discount = new Discount("Descuento porcentaje", 20, TypeDiscount.PERCENTAGE, productId,
                new ObjectId(), new ObjectId(), LocalDateTime.now().plusDays(5));
        ReflectionTestUtils.setField(discount, "id", new ObjectId());

        InvoiceRequestDto request = new InvoiceRequestDto(null, "Cliente de prueba",
                List.of(new InvoiceDetailDto(productId, 2)), 40000, 0, 0);

        when(productRepository.findAllById(any())).thenReturn(List.of(product));
        when(discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(any(), any()))
                .thenReturn(List.of(discount));
        mockActiveStatus();
        when(productRepository.save(any())).thenReturn(product);
        mockInvoiceSave();

        InvoiceResponseDto response = invoiceService.createInvoice(request, new ObjectId());

        assertEquals(50000, response.getSubtotal());
        assertEquals(10000, response.getDiscountTotal());
        assertEquals(40000, response.getTotal());
    }

    @Test
    public void shouldThrowWhenUserIdAndCustomerInfoBothProvided() {
        InvoiceRequestDto request = new InvoiceRequestDto(new ObjectId(), "Cliente de prueba",
                List.of(new InvoiceDetailDto(new ObjectId(), 1)), 25000, 0, 0);

        assertThrows(InvalidCustomerDataException.class,
                () -> invoiceService.createInvoice(request, new ObjectId()));
    }

    @Test
    public void shouldThrowWhenNeitherUserIdNorCustomerInfoProvided() {
        InvoiceRequestDto request = new InvoiceRequestDto(null, null,
                List.of(new InvoiceDetailDto(new ObjectId(), 1)), 25000, 0, 0);

        assertThrows(InvalidCustomerDataException.class,
                () -> invoiceService.createInvoice(request, new ObjectId()));
    }

    @Test
    public void shouldThrowWhenProductDoesNotExist() {
        ObjectId productId = new ObjectId();

        InvoiceRequestDto request = new InvoiceRequestDto(null, "Cliente de prueba",
                List.of(new InvoiceDetailDto(productId, 1)), 25000, 0, 0);

        when(productRepository.findAllById(any())).thenReturn(List.of());
        when(discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(any(), any()))
                .thenReturn(List.of());
        mockActiveStatus();

        assertThrows(NoSuchElementException.class, () -> invoiceService.createInvoice(request, new ObjectId()));
    }

    @Test
    public void shouldThrowWhenStockIsInsufficient() {
        ObjectId productId = new ObjectId();
        Product product = buildProduct(productId, 25000, 1);

        InvoiceRequestDto request = new InvoiceRequestDto(null, "Cliente de prueba",
                List.of(new InvoiceDetailDto(productId, 5)), 125000, 0, 0);

        when(productRepository.findAllById(any())).thenReturn(List.of(product));
        when(discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(any(), any()))
                .thenReturn(List.of());
        mockActiveStatus();

        assertThrows(InsufficientStockException.class,
                () -> invoiceService.createInvoice(request, new ObjectId()));
    }

    @Test
    public void shouldThrowWhenTotalDoesNotMatch() {
        ObjectId productId = new ObjectId();
        Product product = buildProduct(productId, 25000, 50);

        InvoiceRequestDto request = new InvoiceRequestDto(null, "Cliente de prueba",
                List.of(new InvoiceDetailDto(productId, 2)), 1, 0, 0);

        when(productRepository.findAllById(any())).thenReturn(List.of(product));
        when(discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(any(), any()))
                .thenReturn(List.of());
        mockActiveStatus();
        when(productRepository.save(any())).thenReturn(product);

        assertThrows(PriceMismatchException.class,
                () -> invoiceService.createInvoice(request, new ObjectId()));
    }
}
