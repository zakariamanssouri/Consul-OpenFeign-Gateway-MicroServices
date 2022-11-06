package me.zakaria.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types = Customer.class)
public interface CustomerProject {
    public Long getId();
    public String getName();
    public String getEmail();
}
