package me.zakaria.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zakaria.orderservice.enums.OrderStatus;
import me.zakaria.orderservice.models.Customer;
import me.zakaria.orderservice.models.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private String description;
    private OrderStatus status;
    private Long customerId;

    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

    @Transient
    private Customer customer;

    @Transient
    private Product product;
}
