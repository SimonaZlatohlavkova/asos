package com.example.asosbe;

import com.example.asosbe.dto.*;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.exception.RegistrationException;
import com.example.asosbe.model.User;
import com.example.asosbe.repository.UserRepository;
import com.example.asosbe.service.IOrderService;
import com.example.asosbe.service.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private IOrderService orderService;

    @InjectMocks
    private UserServiceImpl userService;

    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUserSuccess() throws RegistrationException {
        UserRegistrationRequest request = new UserRegistrationRequest("test@example.com", "StrongPass123@", "John", "Doe");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");

        User savedUser = new User(1L, "Janko", "Testovic", "test@example.com", "hashedPassword", LocalDateTime.now());
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDto result = userService.registerUser(request);

        assertNotNull(result);
        assertEquals("Janko", result.getName());
        assertEquals("Testovic", result.getSurname());
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUserExistingEmail() {
        UserRegistrationRequest request = new UserRegistrationRequest("test@example.com", "StrongPass123@", "John", "Doe");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(RegistrationException.class, () -> userService.registerUser(request));
    }

    @Test
    void testLoginUserSuccess() {
        String email = "test@example.com";
        String password = "password";
        User user = new User(1L, "Janko", "Testovic", "test@example.com", "hashedPassword", LocalDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(true);

        UserDto result = userService.loginUser(new UserLoginRequest(email, password));

        assertNotNull(result.getJwt());
        assertEquals("Janko", result.getName());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testLoginUserInvalidPassword() {
        String email = "test@example.com";
        String password = "password";
        User user =new User(1L, "Janko", "Testovic", "test@example.com", "hashedPassword", LocalDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> userService.loginUser(new UserLoginRequest(email, password)));
    }

    @Test
    void testIsValidToken() {
        String token = Jwts.builder()
                .setSubject("1")
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY)))
                .compact();

        ValidationDto result = userService.isValidToken(token);

        assertTrue(result.getValid());
    }

    @Test
    void testGetUserOrders() {
        Long userId = 1L;

        when(orderService.getOrdersByUserId(userId)).thenReturn(List.of(new OrderResponse()));

        List<OrderResponse> result = userService.getUserOrders(userId);

        assertNotNull(result);
        verify(orderService, times(1)).getOrdersByUserId(userId);
    }

    @Test
    void testGetById() {
        Long userId = 1L;
        User user = new User(1L, "Janko", "Testovic", "test@example.com", "hashedPassword", LocalDateTime.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getById(userId);

        assertNotNull(result);
        assertEquals("Janko", result.getName());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetByIdNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(userId));
    }
}

