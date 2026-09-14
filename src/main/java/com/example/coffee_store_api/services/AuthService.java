package com.example.coffee_store_api.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.coffee_store_api.dto.LoginRequestDto;
import com.example.coffee_store_api.dto.LoginResponseDto;
import com.example.coffee_store_api.dto.UserRequestDto;
import com.example.coffee_store_api.dto.UserResponseDto;
import com.example.coffee_store_api.exceptions.IncorrectDataLoginException;
import com.example.coffee_store_api.exceptions.UserAlreadyExistExceptionHandler;
import com.example.coffee_store_api.models.Role;
import com.example.coffee_store_api.models.Status;
import com.example.coffee_store_api.models.User;
import com.example.coffee_store_api.repositories.RoleRepository;
import com.example.coffee_store_api.repositories.StatusRepository;
import com.example.coffee_store_api.repositories.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository,
            StatusRepository statusRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDto signUp(UserRequestDto request) {

        Optional<User> existUser = userRepository.findByEmail(request.getEmail());

        if (existUser.isPresent())
            throw new UserAlreadyExistExceptionHandler("A user with that email already exists in the system");

        String hashedPasword = passwordEncoder.encode(request.getPassword());

        Status status = statusRepository.findByDescriptionIgnoreCase("active").orElseThrow();

        Role role = roleRepository.findByDescriptionIgnoreCase("customer").orElseThrow();

        User user = new User(request.getName(), request.getLastName(), role.getId(), request.getEmail(), hashedPasword,
                null, status.getId());

        User userSaved = userRepository.save(user);

        return new UserResponseDto(userSaved.getId().toHexString(), userSaved.getName(), userSaved.getLastName(),
                userSaved.getRoleId().toHexString(), userSaved.getEmail(), userSaved.getStatusId().toHexString(),
                userSaved.getCreatedAt(), userSaved.getUpdatedAt(), userSaved.getDeletedAt());

    }

    public LoginResponseDto signIn(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IncorrectDataLoginException("Incorrect email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IncorrectDataLoginException("Incorrect email or password");

        Role role = roleRepository.findById(user.getRoleId()).orElseThrow();

        String token = jwtService.generateToken(user.getId().toHexString(), role.getId().toHexString(),
                role.getDescription());

        return new LoginResponseDto(user.getId().toHexString(), user.getName(), user.getLastName(),
                user.getRoleId().toHexString(), user.getEmail(), user.getStatusId().toHexString(),
                user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt(), token);
    }
}
