package com.example.coffee_store_api.exceptions;

public class PriceMismatchException extends RuntimeException {
    public PriceMismatchException(String message) {
        super(message);
    }
}
