package com.example.coffee_store_api.exceptions;

public class UserAlreadyExistExceptionHandler extends RuntimeException {

    public UserAlreadyExistExceptionHandler(String message) {
        super(message);
    }
}
