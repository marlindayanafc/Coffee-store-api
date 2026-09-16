package com.example.coffee_store_api.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.coffee_store_api.dto.InvoiceDetailDto;
import com.example.coffee_store_api.dto.InvoiceDetailResponseDto;
import com.example.coffee_store_api.dto.InvoiceRequestDto;
import com.example.coffee_store_api.dto.InvoiceResponseDto;
import com.example.coffee_store_api.exceptions.InsufficientStockException;
import com.example.coffee_store_api.exceptions.InvalidCustomerDataException;
import com.example.coffee_store_api.exceptions.PriceMismatchException;
import com.example.coffee_store_api.models.Discount;
import com.example.coffee_store_api.models.Invoice;
import com.example.coffee_store_api.models.InvoiceDetail;
import com.example.coffee_store_api.models.Product;
import com.example.coffee_store_api.models.Status;
import com.example.coffee_store_api.repositories.DiscountRepository;
import com.example.coffee_store_api.repositories.InvoiceRepository;
import com.example.coffee_store_api.repositories.ProductRepository;
import com.example.coffee_store_api.repositories.StatusRepository;
import com.example.coffee_store_api.repositories.UserRepository;

@Service
public class InvoiceService {
        private final InvoiceRepository invoiceRepository;
        private final ProductRepository productRepository;
        private final UserRepository userRepository;
        private final DiscountRepository discountRepository;
        private final StatusRepository statusRepository;

        public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository,
                        UserRepository userRepository, DiscountRepository discountRepository,
                        StatusRepository statusRepository) {
                this.invoiceRepository = invoiceRepository;
                this.productRepository = productRepository;
                this.userRepository = userRepository;
                this.discountRepository = discountRepository;
                this.statusRepository = statusRepository;
        }

        @Transactional
        public InvoiceResponseDto createInvoice(InvoiceRequestDto invoiceRequest, ObjectId userId) {

                if (invoiceRequest.getUserId() != null && invoiceRequest.getCustomerInfo() != null) {
                        throw new InvalidCustomerDataException(
                                        "Solo puedes enviar userId o customerInfo, no ambos");
                } else if (invoiceRequest.getUserId() != null) {
                        userRepository.findById(invoiceRequest.getUserId()).orElseThrow();
                } else if (invoiceRequest.getCustomerInfo() == null) {
                        throw new InvalidCustomerDataException(
                                        "Debes enviar userId o customerInfo");
                }

                List<ObjectId> idsProduct = invoiceRequest.getDetails().stream().map(p -> p.getProductId())
                                .collect(Collectors.toList());

                List<Product> products = productRepository.findAllById(idsProduct);

                List<ObjectId> idsProductBd = products.stream().map(p -> p.getId())
                                .collect(Collectors.toList());

                List<Discount> discounts = discountRepository.findByProductIdInAndDeletedAtIsNullAndExpiredAtAfter(
                                idsProductBd,
                                LocalDateTime.now());

                Status status = statusRepository.findByDescriptionIgnoreCase("active").orElseThrow();

                double total = 0;
                double totalDiscount = 0;
                double subtotal = 0;

                List<InvoiceDetail> details = new ArrayList<>();

                for (InvoiceDetailDto item : invoiceRequest.getDetails()) {
                        if (!idsProductBd.contains(item.getProductId())) {
                                throw new NoSuchElementException("El producto " + item.getProductId() + " no existe");
                        }

                        Product infoProduct = products.stream().filter(p -> p.getId().equals(item.getProductId()))
                                        .findFirst()
                                        .orElseThrow();

                        Optional<Discount> discount = discounts.stream()
                                        .filter(d -> d.getProductId().equals(item.getProductId()))
                                        .findFirst();

                        double lineSubtotal = infoProduct.getPrice() * item.getQuantity();
                        subtotal += lineSubtotal;

                        ObjectId discountId = null;
                        double lineDiscount = 0;

                        if (!discount.isEmpty()) {
                                double discountValue = discount.get().getValue();
                                discountId = discount.get().getId();
                                switch (discount.get().getType()) {
                                        case FIXED:
                                                lineDiscount = discountValue * item.getQuantity();
                                                break;
                                        case PERCENTAGE:
                                                lineDiscount = (infoProduct.getPrice() * discountValue / 100)
                                                                * item.getQuantity();
                                                break;

                                        default:
                                                break;
                                }
                                totalDiscount += lineDiscount;
                        }

                        details.add(new InvoiceDetail(item.getProductId(), item.getQuantity(), infoProduct.getPrice(),
                                        lineSubtotal - lineDiscount, discountId, infoProduct.getName(),
                                        infoProduct.getDescription(), infoProduct.getAdditionalInfo(),
                                        infoProduct.getImage(),
                                        infoProduct.getProductCategoryId(), infoProduct.getCreatedAt(),
                                        infoProduct.getUpdatedAt(),
                                        infoProduct.getCreatedBy()));

                        if (infoProduct.getStock() < item.getQuantity()) {
                                throw new InsufficientStockException(
                                                "Stock insuficiente para el producto " + infoProduct.getName());
                        }

                        infoProduct.setStock(infoProduct.getStock() - item.getQuantity());

                        productRepository.save(infoProduct);

                }

                total = subtotal - totalDiscount;

                if (Math.abs(total - invoiceRequest.getTotal()) > 0.01) {
                        throw new PriceMismatchException(
                                        "El total calculado no coincide con el total enviado");
                }

                Invoice invoice = new Invoice(invoiceRequest.getUserId(), invoiceRequest.getCustomerInfo(), details,
                                status.getId(), total, subtotal, totalDiscount, userId);

                Invoice invoiceSaved = invoiceRepository.save(invoice);

                return toInvoiceResponseDto(invoiceSaved);
        }

        public InvoiceResponseDto getInvoiceDetail(ObjectId id) {
                Invoice invoice = invoiceRepository.findById(id).orElseThrow();
                return toInvoiceResponseDto(invoice);
        }

        public Page<InvoiceResponseDto> getInvoices(Pageable pageable, String search) {

                return invoiceRepository.findByDeletedAtIsNull(pageable, search)
                                .map(this::toInvoiceResponseDto);
        }

        public InvoiceResponseDto cancelInvoice(ObjectId id, String cancelReason, ObjectId userId) {
                Invoice invoice = invoiceRepository.findById(id).orElseThrow();

                Status status = statusRepository.findByDescriptionIgnoreCase("cancelled").orElseThrow();

                invoice.setCancelledBy(userId);
                invoice.setCancelledAt(LocalDateTime.now());
                invoice.setUpdatedAt(LocalDateTime.now());
                invoice.setCancelReason(cancelReason);
                invoice.setStatusId(status.getId());

                invoiceRepository.save(invoice);

                return toInvoiceResponseDto(invoice);

        }

        private InvoiceResponseDto toInvoiceResponseDto(Invoice invoice) {
                List<InvoiceDetailResponseDto> detailResponse = invoice.getDetails().stream()
                                .map(item -> new InvoiceDetailResponseDto(item.getProductId().toHexString(),
                                                item.getQuantity(),
                                                item.getUnitPrice(), item.getTotalPrice(),
                                                item.getDiscountId() != null ? item.getDiscountId().toHexString()
                                                                : null,
                                                item.getProductName(),
                                                item.getProductDescription(), item.getProductAdditionalInfo(),
                                                item.getProductImage(),
                                                item.getProductCategoryId().toHexString(), item.getProductCreatedAt(),
                                                item.getProductUpdatedAt(), item.getProductCreatedBy().toHexString()))
                                .collect(Collectors.toList());

                return new InvoiceResponseDto(invoice.getId().toHexString(),
                                invoice.getUserId() != null ? invoice.getUserId().toHexString() : null,
                                invoice.getCustomerInfo(), detailResponse,
                                invoice.getStatusId().toHexString(), invoice.getTotal(),
                                invoice.getSubtotal(),
                                invoice.getDiscountTotal(), invoice.getCreatedBy().toHexString(),
                                invoice.getCancelledBy() != null ? invoice.getCancelledBy().toHexString()
                                                : null,
                                invoice.getCancelReason(),
                                invoice.getCancelledAt(), invoice.getCreatedAt(), invoice.getUpdatedAt(),
                                invoice.getDeletedAt());
        }
}
