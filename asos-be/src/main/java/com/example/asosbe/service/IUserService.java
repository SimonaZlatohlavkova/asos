package com.example.asosbe.service;

import com.example.asosbe.dto.*;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.exception.RegistrationException;
import com.example.asosbe.model.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface IUserService {

    UserDto registerUser(UserRegistrationRequest registrationRequest) throws RegistrationException;

    UserDto loginUser(UserLoginRequest loginRequest) throws NotFoundException;

    ValidationDto isValidToken(String jwt);

    List<OrderResponse> getUserOrders(Long userId);

    User getById(Long id) throws NotFoundException;

    Long getUserIdByToken(String jwt) throws LoginException;

    UserProfileResponse getUserProfile(Long userId);
}
