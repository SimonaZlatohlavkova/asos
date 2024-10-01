package products.models;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String imageUrl;
    private double price;
    private double weight;
    private String productComposition;
    @Column(nullable = false)
    private String categoryName;
}
