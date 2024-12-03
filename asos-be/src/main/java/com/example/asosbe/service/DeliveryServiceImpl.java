package com.example.asosbe.service;

import com.example.asosbe.exception.NotFoundException;
import com.example.asosbe.dto.DeliveryResponse;
import com.example.asosbe.model.Delivery;
import com.example.asosbe.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DeliveryServiceImpl implements IDeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery getDeliveryById(Long id) {
        log.info("getDeliveryById({})", id);
        var delivery = deliveryRepository.findById(id);
        if (delivery.isEmpty()){
            throw new NotFoundException("Requested delivery not found");
        }
        return delivery.get();
    }

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        log.info("saveDelivery({})", delivery.getName());
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteDelivery(Long id) {
        log.info("deleteDelivery({})", id);
        deliveryRepository.deleteById(id);
    }

    @Override
    public List<DeliveryResponse> getDeliveries() {
        log.info("getDeliveries()");
        return deliveryRepository.findAll().stream()
                .map(this::convertDeliveryToDeliveryResponse)
                .toList();
    }

    private DeliveryResponse convertDeliveryToDeliveryResponse(Delivery delivery) {
        DeliveryResponse deliveryResponse = new DeliveryResponse();

        deliveryResponse.setDeliveryId(delivery.getId());
        deliveryResponse.setName(delivery.getName());
        deliveryResponse.setPrice(delivery.getPrice());

        return deliveryResponse;
    }
}
