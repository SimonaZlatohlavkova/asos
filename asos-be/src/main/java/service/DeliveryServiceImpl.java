package service;

import dto.DeliveryResponse;
import exception.NotFoundException;
import model.Delivery;
import repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements IDeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery getDeliveryById(Long id) {
        var delivery = deliveryRepository.findById(id);
        if (delivery.isEmpty()){
            throw new  NotFoundException("Requested delivery not found");
        }
        return delivery.get();
    }

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public List<DeliveryResponse> getDeliveries() {
        return deliveryRepository.findAll().stream()
                .map(this::convertDeliveryToDeliveryResponse)
                .collect(Collectors.toList());
    }

    private DeliveryResponse convertDeliveryToDeliveryResponse(Delivery delivery) {
        DeliveryResponse deliveryResponse = new DeliveryResponse();

        deliveryResponse.setDeliveryId(delivery.getDeliveryId());
        deliveryResponse.setName(delivery.getName());
        deliveryResponse.setPrice(delivery.getPrice());

        return deliveryResponse;
    }
}
