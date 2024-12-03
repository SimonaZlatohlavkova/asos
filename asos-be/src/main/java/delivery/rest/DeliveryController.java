package delivery.rest;

import delivery.dtos.DeliveryResponse;
import delivery.service.IDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@AllArgsConstructor
public class DeliveryController {

    private IDeliveryService iDeliveryService;

    @GetMapping()
    public ResponseEntity<List<DeliveryResponse>> getDelivery() {
        return ResponseEntity.ok(this.iDeliveryService.getDeliveries());
    }
}
