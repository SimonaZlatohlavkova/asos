package rest;

import dto.UserDto;
import dto.UserLoginRequest;
import dto.UserRegistrationRequest;
import dto.ValidationDto;
import exception.ErrorResponse;
import exception.NotFoundException;
import exception.RegistrationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserRest {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserDto registeredUser = userService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), e.getClass().getName()));
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
}

