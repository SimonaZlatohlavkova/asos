package com.example.asosbe.service;

import com.example.asosbe.dto.*;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.exception.RegistrationException;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {

    UserDto registerUser(UserRegistrationRequest registrationRequest) throws RegistrationException;

    UserDto loginUser(UserLoginRequest loginRequest) throws NotFoundException;

    ValidationDto isValidToken(String jwt);

    List<OrderResponse> getUserOrders(Long userId);

    Long getUserIdByToken(String jwt) throws LoginException;

    UserProfileResponse getUserProfile(Long userId);
}
