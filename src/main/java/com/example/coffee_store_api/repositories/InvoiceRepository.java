package com.example.coffee_store_api.repositories;

import com.example.coffee_store_api.models.Invoice;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InvoiceRepository extends MongoRepository<Invoice, ObjectId> {
    Page<Invoice> findByDeletedAtIsNull(Pageable pageable, String search);

    Optional<Invoice> findByIdAndDeletedAtIsNull(ObjectId id);
}
