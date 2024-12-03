package delivery.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryResponse {
    private Long deliveryId;
    private String name;
    private BigDecimal price;
}
