package order.dtos;

import address.dtos.AddressRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import products.dtos.ProductRequest;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    private Long deliveryId;
    private AddressRequest address;
    private List<ProductRequest> products;
}
