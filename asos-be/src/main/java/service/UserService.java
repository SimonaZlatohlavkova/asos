package service;

import dto.UserLoginRequest;
import dto.UserRegistrationRequest;
import dto.ValidationDto;
import exception.NotFoundException;
import dto.UserDto;
import exception.RegistrationException;

import java.security.PublicKey;
import java.util.Map;

public interface UserService {

    UserDto registerUser(UserRegistrationRequest registrationRequest) throws RegistrationException;

    UserDto loginUser(UserLoginRequest loginRequest) throws NotFoundException;

    ValidationDto isValidToken(String jwt);

    Long getUserIdByToken(String jwt);

    Map<String, String> encrypt(byte[] data, PublicKey publicKey) throws Exception;

    PublicKey getPublicKeyFromString(String key) throws Exception;

}
