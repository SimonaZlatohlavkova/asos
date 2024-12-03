package com.example.asosbe.service;

import com.example.asosbe.dto.AddressRequest;
import com.example.asosbe.dto.OrderRequest;
import com.example.asosbe.dto.OrderResponse;
import com.example.asosbe.dto.ProductRequest;
import com.example.asosbe.mapper.OrderMapper;
import com.example.asosbe.model.Address;
import com.example.asosbe.model.Delivery;
import com.example.asosbe.model.Order;
import com.example.asosbe.model.Product;
import com.example.asosbe.repository.OrderRepository;
import com.example.asosbe.service.IAddressService;
import com.example.asosbe.service.IDeliveryService;
import com.example.asosbe.service.IProductService;
import com.example.asosbe.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private IProductService productService;

    @Mock
    private IAddressService addressService;

    @Mock
    private IDeliveryService deliveryService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrdersByUserId() {
        Long userId = 1L;
        Order mockOrder = new Order();
        mockOrder.setId(1L);
        OrderResponse mockResponse = new OrderResponse();
        mockResponse.setId(25L);

        when(orderRepository.findByUser_Id(userId)).thenReturn(List.of(mockOrder));
        when(orderMapper.toOrderResponse(mockOrder)).thenReturn(mockResponse);

        List<OrderResponse> result = orderService.getOrdersByUserId(userId);

        assertEquals(1, result.size());
        assertEquals(25L, result.get(0).getId());
        verify(orderRepository, times(1)).findByUser_Id(userId);
        verify(orderMapper, times(1)).toOrderResponse(mockOrder);
    }

    @Test
    void testCreateOrderSuccess() {
        Long addressId = 1L;
        Long deliveryId = 1L;
        Long productId = 1L;
        BigDecimal productPrice = BigDecimal.valueOf(100);

        OrderRequest mockRequest = new OrderRequest();
        mockRequest.setAddress(new AddressRequest("street", "city", "house num",
                "postalCode", "country"));
        mockRequest.setDeliveryId(deliveryId);
        mockRequest.setProducts(List.of(new ProductRequest(1L, BigDecimal.ONE, 1L)));

        Address mockAddress = new Address();
        mockAddress.setId(addressId);

        Delivery mockDelivery = new Delivery();
        mockDelivery.setId(deliveryId);

        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setOriginalPrice(productPrice);

        when(deliveryService.getDeliveryById(deliveryId)).thenReturn(mockDelivery);
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        orderService.createOrder(mockRequest);

        verify(deliveryService, times(1)).getDeliveryById(deliveryId);
        verify(productService, times(1)).getProductById(productId);
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
