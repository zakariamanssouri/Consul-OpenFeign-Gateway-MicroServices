package me.zakaria.orderservice.services;

import me.zakaria.orderservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestService {
    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable(name = "id") Long id);

    @GetMapping("/customers?projection=fullCustomer")
    public PagedModel<Customer> allCustomers();
}
