package com.example.asosbe.rest;

import com.example.asosbe.dto.UserDto;
import com.example.asosbe.dto.UserLoginRequest;
import com.example.asosbe.dto.UserRegistrationRequest;
import com.example.asosbe.dto.ValidationDto;
import com.example.asosbe.exception.ErrorResponse;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.exception.RegistrationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.asosbe.service.IUserService;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserRest {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserDto registeredUser = userService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage(),
                    HttpStatus.BAD_REQUEST.value(), e.getClass().getName()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginRequest loginRequest) {
        try {
            UserDto loggedUser = userService.loginUser(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), e.getClass().getName()));
        }
    }

    @GetMapping("/validation")
    public ResponseEntity<ValidationDto> isValidToken(@RequestHeader("Authorization") String jwt) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.isValidToken(jwt));
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getUserProfile(@RequestHeader("Authorization") String jwt) {
        Long userId = null;
        try{
            userId = userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserProfile(userId));
    }

    @GetMapping("/orders")
    public ResponseEntity<Object> getUserOrders(@RequestHeader("Authorization") String jwt) {
        Long userId = null;
        try{
            userId = userService.getUserIdByToken(jwt);
        }
        catch(LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), e.getClass().getName()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserOrders(userId));
    }
}

