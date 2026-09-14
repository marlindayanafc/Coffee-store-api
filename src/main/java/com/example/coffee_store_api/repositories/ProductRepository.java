package com.example.coffee_store_api.repositories;

import com.example.coffee_store_api.models.Product;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    Page<Product> findByDeletedAtIsNullAndNameContainingIgnoreCase(String search, Pageable pageable);

    Optional<Product> findByIdAndDeletedAtIsNull(ObjectId id);
}
