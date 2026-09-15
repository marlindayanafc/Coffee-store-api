package com.example.coffee_store_api.exceptions;

public class InvalidDiscountDateException extends RuntimeException {
    public InvalidDiscountDateException(String message) {
        super(message);
    }
}
