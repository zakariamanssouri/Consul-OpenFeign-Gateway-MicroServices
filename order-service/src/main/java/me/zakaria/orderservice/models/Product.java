package me.zakaria.orderservice.models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;

}
