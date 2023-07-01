package com.cloth_warehouse.assignment_1;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloth_warehouse.assignment_1.repository.ClothesRepository;
import com.cloth_warehouse.assignment_1.models.Clothe;
import com.cloth_warehouse.assignment_1.models.Clothe.Brand;


@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ClothesRepository repository) {
		return args -> {
			repository.save(Clothe.builder()
					.name("T-shirt")
					.brand(Clothe.Brand.Adidas)
					.establishmentYear(2022)
					.price(new BigDecimal("2211.21"))
					.build());

			repository.save(Clothe.builder()
					.name("Jean Jacket")
					.brand(Clothe.Brand.Levi)
					.establishmentYear(2022)
					.price(new BigDecimal("3123.26"))
					.build());

			repository.save(Clothe.builder()
					.name("Long Sleeve Shirt")
					.brand(Clothe.Brand.Uniqlo)
					.establishmentYear(2021)
					.price(new BigDecimal("2123.12"))
					.build());

			repository.save(Clothe.builder()
					.name("Running Shoes")
					.brand(Clothe.Brand.Nike)
					.establishmentYear(2022)
					.price(new BigDecimal("2033.22"))
					.build());

			repository.save(Clothe.builder()
					.name("Wind Breaker")
					.brand(Clothe.Brand.Nike)
					.establishmentYear(2022)
					.price(new BigDecimal("2222.22"))
					.build());

			repository.save(Clothe.builder()
					.name("Skirt")
					.brand(Clothe.Brand.Zara)
					.establishmentYear(2022)
					.price(new BigDecimal("4032.50"))
					.build());
		};
	}
}
