package com.example.asosbe.service;

import com.example.asosbe.dto.DeliveryResponse;
import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.model.Delivery;
import com.example.asosbe.repository.DeliveryRepository;
import com.example.asosbe.service.DeliveryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDeliveryByIdSuccess() {
        Long deliveryId = 1L;
        Delivery mockDelivery = new Delivery();
        mockDelivery.setId(deliveryId);
        mockDelivery.setName("Test Delivery");
        when(deliveryRepository.findById(deliveryId)).thenReturn(Optional.of(mockDelivery));

        Delivery result = deliveryService.getDeliveryById(deliveryId);

        assertNotNull(result);
        assertEquals(deliveryId, result.getId());
        assertEquals("Test Delivery", result.getName());
        verify(deliveryRepository, times(1)).findById(deliveryId);
    }

    @Test
    void testGetDeliveryByIdNotFound() {
        Long deliveryId = 2L;
        when(deliveryRepository.findById(deliveryId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> deliveryService.getDeliveryById(deliveryId));
        assertEquals("Requested delivery not found", exception.getMessage());
        verify(deliveryRepository, times(1)).findById(deliveryId);
    }

    @Test
    void testGetDeliveries() {
        Delivery delivery1 = new Delivery();
        delivery1.setId(1L);
        delivery1.setName("Delivery One");

        Delivery delivery2 = new Delivery();
        delivery2.setId(2L);
        delivery2.setName("Delivery Two");

        when(deliveryRepository.findAll()).thenReturn(List.of(delivery1, delivery2));

        List<DeliveryResponse> result = deliveryService.getDeliveries();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Delivery One", result.get(0).getName());
        assertEquals("Delivery Two", result.get(1).getName());
        verify(deliveryRepository, times(1)).findAll();
    }
}
