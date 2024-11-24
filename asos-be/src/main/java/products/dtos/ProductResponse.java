package products.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private String name;
    private String url;
    private String description;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private Long stock;
}
