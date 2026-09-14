package com.example.coffee_store_api.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.coffee_store_api.models.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndDeletedAtIsNull(ObjectId id);

    List<User> findByDeletedAtIsNull();
}
