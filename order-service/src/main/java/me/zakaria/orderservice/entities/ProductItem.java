package me.zakaria.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zakaria.orderservice.models.Product;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private double price;
    private double discount;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    @Transient
    private Product product;
}
