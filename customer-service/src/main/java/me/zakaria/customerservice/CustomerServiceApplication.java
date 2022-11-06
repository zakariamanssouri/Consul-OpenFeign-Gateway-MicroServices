package me.zakaria.customerservice;

import me.zakaria.customerservice.entities.Customer;
import me.zakaria.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	//generate command line runner to test the service
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			Customer zakaria = Customer.builder().name("zakaria").email("zakair@df.Com").build();

			customerRepository.saveAll(List.of(
					zakaria,
					Customer.builder().name("hamid").email("azk@q.Com").build(),
					Customer.builder().name("samir").email("azk@q.Com").build()
			));

			customerRepository.findAll().forEach(System.out::println);


		};

	}


}
