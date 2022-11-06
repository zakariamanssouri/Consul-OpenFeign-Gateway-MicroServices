package me.zakaria.orderservice.services;

import me.zakaria.orderservice.models.Customer;
import me.zakaria.orderservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryRestService {
    @GetMapping("/products/{id}?projection=fullProduct")
    public Product inventoryById(@PathVariable(name = "id") Long id);

    @GetMapping("/products?projection=fullProduct")
    public PagedModel<Product> allProducts();
}
