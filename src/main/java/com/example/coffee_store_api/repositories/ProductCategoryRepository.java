package com.example.coffee_store_api.repositories;

import com.example.coffee_store_api.models.ProductCategory;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, ObjectId> {
    Page<ProductCategory> findByDeletedAtIsNullAndNameContainingIgnoreCase(String search, Pageable pageable);

    Optional<ProductCategory> findByIdAndDeletedAtIsNull(ObjectId id);
}
