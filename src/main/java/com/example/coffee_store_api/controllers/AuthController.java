package com.example.coffee_store_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee_store_api.dto.LoginRequestDto;
import com.example.coffee_store_api.dto.LoginResponseDto;
import com.example.coffee_store_api.dto.UserRequestDto;
import com.example.coffee_store_api.dto.UserResponseDto;
import com.example.coffee_store_api.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserResponseDto signUp(@Valid @RequestBody UserRequestDto userRequest) {
        return authService.signUp(userRequest);
    }

    @PostMapping("/signin")
    public LoginResponseDto signIn(@Valid @RequestBody LoginRequestDto loginRequest) {
        return authService.signIn(loginRequest);
    }

}
