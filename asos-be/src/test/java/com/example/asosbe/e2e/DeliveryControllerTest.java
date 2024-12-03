package com.example.asosbe.e2e;

import com.example.asosbe.dto.DeliveryResponse;
import com.example.asosbe.rest.DeliveryController;
import com.example.asosbe.service.IDeliveryService;
import com.example.asosbe.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.security.auth.login.LoginException;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDeliveryService deliveryService;

    @MockBean
    private IUserService userService;

    @BeforeEach
    void setUp() {
        Mockito.reset(deliveryService, userService);
    }

    @Test
    void testGetDeliverySuccess() throws Exception {
        String jwt = "valid.jwt.token";
        List<DeliveryResponse> deliveries = List.of(
                new DeliveryResponse(1L, "Kuriér DPD", BigDecimal.valueOf(1,2)),
                new DeliveryResponse(2L, "Kuriér SPS", BigDecimal.valueOf(1,2))
        );

        when(userService.getUserIdByToken(jwt)).thenReturn(1L);
        when(deliveryService.getDeliveries()).thenReturn(deliveries);

        mockMvc.perform(get("/delivery")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Kuriér DPD"))
                .andExpect(jsonPath("$[0].price").value(1.2))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Kuriér SPS"))
                .andExpect(jsonPath("$[1].price").value(3.8));
    }

    @Test
    void testGetDeliveryUnauthorized() throws Exception {
        String jwt = "invalid.jwt.token";

        when(userService.getUserIdByToken(jwt)).thenThrow(new LoginException("Invalid token"));

        mockMvc.perform(get("/delivery")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid token"))
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.errorType").value("javax.security.auth.login.LoginException"));
    }
}

