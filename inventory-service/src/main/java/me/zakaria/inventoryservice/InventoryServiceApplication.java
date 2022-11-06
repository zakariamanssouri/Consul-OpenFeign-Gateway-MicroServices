package me.zakaria.inventoryservice;

import me.zakaria.inventoryservice.entities.Product;
import me.zakaria.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
		CommandLineRunner start(InventoryRepository inventoryRepository){
			return args -> {
	            inventoryRepository.saveAll(List.of(
						new Product(null, "Ordinateur", 1000, 10),
	                    new Product(null, "Imprimante", 500, 20),
	                    new Product(null, "Smartphone", 700, 30)
				));
			};
		}
}
