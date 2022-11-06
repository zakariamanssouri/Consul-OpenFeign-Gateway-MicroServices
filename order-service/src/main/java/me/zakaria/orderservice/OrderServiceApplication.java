package me.zakaria.orderservice;

import me.zakaria.orderservice.entities.Order;
import me.zakaria.orderservice.entities.ProductItem;
import me.zakaria.orderservice.enums.OrderStatus;
import me.zakaria.orderservice.models.Customer;
import me.zakaria.orderservice.models.Product;
import me.zakaria.orderservice.repository.OrderRepository;
import me.zakaria.orderservice.repository.ProductItemRepository;
import me.zakaria.orderservice.services.CustomerRestService;
import me.zakaria.orderservice.services.InventoryRestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(
			OrderRepository orderRepository,
			ProductItemRepository productItemRepository,
			CustomerRestService customerRestService,
			InventoryRestService inventoryRestService
	) {

		return args -> {
			List<Customer> customers = customerRestService.allCustomers().getContent().stream().toList();
			List<Product> products = inventoryRestService.allProducts().getContent().stream().toList();
			Long customerId = 1L;
			Random random = new Random();
			Customer customer = customerRestService.customerById(customerId);

			for (int i = 0; i < 20; i++) {
				Order order = Order.builder().customerId(customers.get(random.nextInt(customers.size())).getId()).
				status(Math.random() < 0.5 ? OrderStatus.CREATED :OrderStatus.PENDING).
				createdAt(new Date()).build();
				Order savedOrder = orderRepository.save(order);

				for (Product product : products) {
					if(Math.random() < 0.5) {
						ProductItem productItem = ProductItem.builder()
								.order(savedOrder)
								.productId(product.getId())
								.price(product.getId())
								.quantity(1 + random.nextInt(10))
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);

					}
				}
			}
		};
	}
}
