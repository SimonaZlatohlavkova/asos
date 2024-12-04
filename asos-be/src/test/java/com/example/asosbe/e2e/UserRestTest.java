package com.example.asosbe.e2e;

import com.example.asosbe.config.TestSecurityConfiguration;

import com.example.asosbe.dto.*;
import com.example.asosbe.rest.UserRest;
import com.example.asosbe.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRest.class)
@Import(TestSecurityConfiguration.class)
class UserRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Test
    void testRegisterUserSuccess() throws Exception {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest("John", "Doe", "john@example.com", "Password123");
        UserDto userDto = new UserDto(1L, "John", "Doe", "john@example.com", null);

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "John",
                                "surname": "Doe",
                                "email": "john@example.com",
                                "password": "Password123"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testLoginUserSuccess() throws Exception {
        UserLoginRequest loginRequest = new UserLoginRequest("john@example.com", "Password123");
        UserDto userDto = new UserDto(1L, "John", "Doe", "john@example.com", "jwt.token");

        when(userService.loginUser(any(UserLoginRequest.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "email": "john@example.com",
                                "password": "Password123"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.jwt").value("jwt.token"));
    }

    @Test
    void testIsValidTokenSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        ValidationDto validationDto = new ValidationDto(true);

        when(userService.isValidToken(jwt)).thenReturn(validationDto);

        mockMvc.perform(get("/api/validation")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true));
    }

    @Test
    void testGetUserProfileSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        Long userId = 1L;

        when(userService.getUserIdByToken(jwt)).thenReturn(userId);
        when(userService.getUserProfile(userId)).thenReturn(new UserProfileResponse("John", "Doe", "john@example.com"));

        mockMvc.perform(get("/api/profile")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testGetUserOrdersSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        Long userId = 1L;

        when(userService.getUserIdByToken(jwt)).thenReturn(userId);
        when(userService.getUserOrders(userId)).thenReturn(List.of(
                new OrderResponse(1L, LocalDateTime.now(), BigDecimal.valueOf(100), BigDecimal.valueOf(5), null, "pending", null),
                new OrderResponse(2L, LocalDateTime.now(), BigDecimal.valueOf(200), BigDecimal.valueOf(10), null, "delivered", null)
        ));

        mockMvc.perform(get("/api/orders")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].status").value("pending"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].status").value("delivered"));
    }

    @Test
    void testUnauthorizedAccess() throws Exception {
        String jwt = "invalid.jwt.token";

        when(userService.getUserIdByToken(jwt)).thenThrow(new LoginException("Invalid token"));

        mockMvc.perform(get("/api/profile")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid token"))
                .andExpect(jsonPath("$.status").value(401));
    }
}

