package com.example.coffee_store_api.repositories;

import com.example.coffee_store_api.models.Discount;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiscountRepository extends MongoRepository<Discount, ObjectId> {
    Page<Discount> findByDeletedAtIsNullAndDescriptionContainingIgnoreCase(String search, Pageable pageable);

    Optional<Discount> findByIdAndDeletedAtIsNull(ObjectId id);
}
