package com.example.asosbe.service;

import com.example.asosbe.model.Delivery;
import com.example.asosbe.dto.DeliveryResponse;

import java.util.List;

public interface IDeliveryService {

    Delivery getDeliveryById(Long id);
    Delivery saveDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    List<DeliveryResponse> getDeliveries();
}
