package com.example.coffee_store_api.models;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    private ObjectId id;
    private String name;
    private String lastName;
    private ObjectId roleId;
    private String email;
    private String password;
    private String validationCode;
    private ObjectId statusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public User() {
    }

    public User(String name, String lastName, ObjectId roleId, String email, String password, String validationCode,
            ObjectId statusId) {
        this.name = name;
        this.lastName = lastName;
        this.roleId = roleId;
        this.email = email;
        this.password = password;
        this.validationCode = validationCode;
        this.statusId = statusId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public ObjectId getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public ObjectId getRoleId() {
        return this.roleId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getValidationCode() {
        return this.validationCode;
    }

    public ObjectId getStatusId() {
        return this.statusId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleId(ObjectId roleId) {
        this.roleId = roleId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public void setStatusId(ObjectId statusId) {
        this.statusId = statusId;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
