package com.example.asosbe.service;

import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.model.Address;
import com.example.asosbe.repository.AddressRepository;
import com.example.asosbe.service.AddressServiceImpl;
import com.example.asosbe.service.IAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByIdSuccess() {
        Long addressId = 1L;
        Address mockAddress = new Address();
        mockAddress.setId(addressId);
        mockAddress.setStreet("Ulica");
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(mockAddress));

        Address result = addressService.getById(addressId);

        assertNotNull(result);
        assertEquals(addressId, result.getId());
        assertEquals(mockAddress.getStreet(), result.getStreet());
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        Long addressId = 2L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> addressService.getById(addressId));
        assertEquals("Requested address not found", exception.getMessage());
        verify(addressRepository, times(1)).findById(addressId);
    }
}
