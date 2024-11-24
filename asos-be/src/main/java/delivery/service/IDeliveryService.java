package delivery.service;

import delivery.dtos.DeliveryResponse;
import delivery.models.Delivery;

import java.util.List;

public interface IDeliveryService {

    Delivery getDeliveryById(Long id);
    Delivery saveDelivery(Delivery delivery);
    void deleteDelivery(Long id);
    List<DeliveryResponse> getDeliveries();
}
