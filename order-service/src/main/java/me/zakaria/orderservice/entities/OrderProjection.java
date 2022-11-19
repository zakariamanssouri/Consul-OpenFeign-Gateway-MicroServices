package me.zakaria.orderservice.entities;

import me.zakaria.orderservice.enums.OrderStatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullOrder", types = Order.class)
public interface OrderProjection {
    public Long getId();
    Date getCreatedAt();
    Long getCustomerId();
    OrderStatus getStatus();
}
