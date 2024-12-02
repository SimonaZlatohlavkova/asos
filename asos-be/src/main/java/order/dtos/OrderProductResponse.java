package order.dtos;

import address.dtos.AddressResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import products.dtos.ProductResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductResponse {
    private Long id;
    private BigDecimal price;
    private Long quantity;
    private String name;
    private String url;
}