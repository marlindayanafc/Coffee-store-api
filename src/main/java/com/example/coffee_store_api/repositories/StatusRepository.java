package com.example.coffee_store_api.repositories;

import com.example.coffee_store_api.models.Status;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StatusRepository extends MongoRepository<Status, ObjectId> {
    Optional<Status> findByDescriptionIgnoreCase(String description);
}
