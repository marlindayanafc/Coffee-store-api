package com.example.coffee_store_api.repositories;

import com.example.coffee_store_api.models.Role;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Optional<Role> findByDescriptionIgnoreCase(String description);
}
