package com.example.coffee_store_api.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.example.coffee_store_api.dto.ProductRequestDto;
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

    public Product createProduct(ProductRequestDto request, ObjectId userId) {

        Status status = statusRepository.findByDescriptionIgnoreCase("active").orElseThrow();

        Product newProduct = new Product(request.getName(), request.getPrice(), request.getStock(), status.getId(),
                request.getDescription(), request.getAdditionalInfo(), request.getProductCategoryId(),
                "https://pixabay.com/es/photos/caf%C3%A9-granos-de-caf%C3%A9-taza-frijoles-1324126/", userId);

        return productRepository.save(newProduct);

    }
}
