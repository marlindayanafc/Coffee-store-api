package com.example.coffee_store_api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.coffee_store_api.dto.ProductRequestDto;
import com.example.coffee_store_api.dto.ProductResponseDto;
import com.example.coffee_store_api.models.Product;
import com.example.coffee_store_api.models.Status;
import com.example.coffee_store_api.repositories.ProductRepository;
import com.example.coffee_store_api.repositories.StatusRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;

    public ProductService(ProductRepository productRepository, StatusRepository statusRepository) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto request, ObjectId userId) {

        Status status = statusRepository.findByDescriptionIgnoreCase("active").orElseThrow();

        Product newProduct = new Product(request.getName(), request.getPrice(), request.getStock(), status.getId(),
                request.getDescription(), request.getAdditionalInfo(), request.getProductCategoryId(),
                "https://pixabay.com/es/photos/caf%C3%A9-granos-de-caf%C3%A9-taza-frijoles-1324126/", userId);

        return toResponseDto(productRepository.save(newProduct));

    }

    public ProductResponseDto updateProduct(ProductRequestDto request, ObjectId id) {

        Product product = productRepository.findById(id).orElseThrow();

        product.setName(request.getName());
        product.setAdditionalInfo(request.getAdditionalInfo());
        product.setPrice(request.getPrice());
        product.setProductCategoryId(request.getProductCategoryId());
        product.setStock(request.getStock());
        product.setDescription(request.getDescription());
        product.setUpdatedAt(LocalDateTime.now());

        return toResponseDto(productRepository.save(product));

    }

    public ProductResponseDto getProduct(ObjectId id) {
        return toResponseDto(productRepository.findById(id).orElseThrow());
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    public Page<ProductResponseDto> getProducts(Pageable pageable, String search) {
        return productRepository.findByDeletedAtIsNullAndNameContainingIgnoreCase(search, pageable)
                .map(this::toResponseDto);
    }

    public String deleteProduct(ObjectId id) {
        Product product = productRepository.findById(id).orElseThrow();

        product.setDeletedAt(LocalDateTime.now());

        productRepository.save(product);

        return "Product deleted succesfully";

    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(product.getId().toHexString(), product.getName(), product.getPrice(),
                product.getStock(), product.getStatusId().toHexString(), product.getDescription(),
                product.getAdditionalInfo(), product.getProductCategoryId().toHexString(), product.getImage(),
                product.getCreatedBy().toHexString(), product.getCreatedAt(), product.getUpdatedAt(),
                product.getDeletedAt());
    }
}
