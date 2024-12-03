package order.dtos;

import address.dtos.AddressRequest;
import address.models.Address;
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
    private Address address;
    private List<ProductRequest> products;
}
