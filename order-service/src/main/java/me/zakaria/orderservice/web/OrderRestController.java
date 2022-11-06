package me.zakaria.orderservice.web;

import lombok.AllArgsConstructor;
import me.zakaria.orderservice.entities.Order;
import me.zakaria.orderservice.entities.ProductItem;
import me.zakaria.orderservice.models.Product;
import me.zakaria.orderservice.repository.OrderRepository;
import me.zakaria.orderservice.repository.ProductItemRepository;
import me.zakaria.orderservice.services.CustomerRestService;
import me.zakaria.orderservice.services.InventoryRestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @AllArgsConstructor
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestService customerRestService;
    private InventoryRestService inventoryRestService;


    @GetMapping("/fullOrder/{id}")
    public Order getFullOrder(@PathVariable(name = "id") Long id) {
        Order order = orderRepository.findById(id).get();
        order.setCustomer(customerRestService.customerById(order.getCustomerId()));

        order.getProductItems().forEach(pi -> {
            Product product = inventoryRestService.inventoryById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }


}
