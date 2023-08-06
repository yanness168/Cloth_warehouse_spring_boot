package com.distributioncenter;

import java.math.BigDecimal;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.distributioncenter.Models.Item;
import com.distributioncenter.Models.Item.Brand;
import com.distributioncenter.Models.DistributionCenter;
import com.distributioncenter.Models.dto.CreateItem;
import com.distributioncenter.Repository.DistributionCenterRepository;
import com.distributioncenter.Repository.ItemRepository;

import java.util.List;

@SpringBootApplication
public class DistributionCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributionCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository itemRepository,
										DistributionCenterRepository distributionCenterRepository) {
		return args -> {
			itemRepository.save(Item.builder()
					.name("'T-Shirt")
					.brand(Brand.Nike)
					.establishmentYear(2021)
					.price(new BigDecimal(39.99))
					.quantity(10).build());

			itemRepository.save(Item.builder()
					.name("Hat")
					.brand(Brand.Uniqlo)
					.establishmentYear(2022)
					.price(new BigDecimal(20.49))
					.quantity(5).build());

			itemRepository.save(Item.builder()
					.name("Sweater")
					.brand(Brand.Zara)
					.establishmentYear(2023)
					.price(new BigDecimal(55.99))
					.quantity(5).build());

			itemRepository.save(Item.builder()
					.name("Jacket")
					.brand(Brand.Zara)
					.establishmentYear(2023)
					.price(new BigDecimal(120.99))
					.quantity(20).build());

			itemRepository.save(Item.builder()
					.name("Running Shoes")
					.brand(Brand.Adidas)
					.establishmentYear(2021)
					.price(new BigDecimal(80.00))
					.quantity(15).build());

			itemRepository.save(Item.builder()
					.name("Jeans")
					.brand(Brand.Zara)
					.establishmentYear(2023)
					.price(new BigDecimal(49.99))
					.quantity(15).build());

			itemRepository.save(Item.builder()
					.name("Dress")
					.brand(Brand.Aritzia)
					.establishmentYear(2022)
					.price(new BigDecimal(49.99))
					.quantity(15).build());

			itemRepository.save(Item.builder()
					.name("Wool Coat")
					.brand(Brand.Aritzia)
					.establishmentYear(2021)
					.price(new BigDecimal(110.99))
					.quantity(15).build());

			itemRepository.save(Item.builder()
					.name("Shorts")
					.brand(Brand.Nike)
					.establishmentYear(2022)
					.price(new BigDecimal(44.50))
					.quantity(15).build());

			distributionCenterRepository.save(DistributionCenter.builder()
					.name("Distribution Center - NB")
					.latitude(45.273918)
					.longitude(-66.067657).build());

			distributionCenterRepository.save(DistributionCenter.builder()
					.name("Distribution Center 2 - ON")
					.latitude(43.856098)
					.longitude(-79.337021).build());

			distributionCenterRepository.save(DistributionCenter.builder()
					.name("Distribution Center - AB")
					.latitude(53.630474)
					.longitude(-113.625641).build());

			distributionCenterRepository.save(DistributionCenter.builder()
					.name("Distribution Center - BC")
					.latitude(50.112778)
					.longitude(-120.789719).build());


		};
	}
}
