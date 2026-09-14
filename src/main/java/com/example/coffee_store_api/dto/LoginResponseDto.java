package com.example.coffee_store_api.dto;

import java.time.LocalDateTime;

public class LoginResponseDto {
    private String id;
    private String name;
    private String lastName;
    private String roleId;
    private String email;
    private String statusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String token;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String id, String name, String lastName, String roleId, String email,
            String statusId, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
            String token) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.roleId = roleId;
        this.email = email;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.token = token;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getStatusId() {
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

    public String getToken() {
        return this.token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
