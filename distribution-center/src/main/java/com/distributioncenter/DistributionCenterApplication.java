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
import java.util.ArrayList;
import java.util.Arrays;


@SpringBootApplication
public class DistributionCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributionCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository itemRepository,
										DistributionCenterRepository distributionCenterRepository) {
		return args -> {

			Item item1 = Item.builder()
							.name("T-Shirt")
							.brand(Brand.Nike)
							.establishmentYear(2021)
							.price(new BigDecimal(39.99))
							.quantity(10).build();

			Item item2 = Item.builder()
							.name("Hat")
							.brand(Brand.Uniqlo)
							.establishmentYear(2022)
							.price(new BigDecimal(20.49))
							.quantity(5).build();

			Item item3 = Item.builder()
							.name("Sweater")
							.brand(Brand.Zara)
							.establishmentYear(2023)
							.price(new BigDecimal(55.99))
							.quantity(5).build();

			Item item4 = Item.builder()
							.name("Jacket")
							.brand(Brand.Zara)
							.establishmentYear(2023)
							.price(new BigDecimal(120.99))
							.quantity(20).build();

			Item item5 = Item.builder()
							.name("Running Shoes")
							.brand(Brand.Adidas)
							.establishmentYear(2021)
							.price(new BigDecimal(80.00))
							.quantity(15).build();

			Item item6 = Item.builder()
							.name("Jeans")
							.brand(Brand.Zara)
							.establishmentYear(2023)
							.price(new BigDecimal(49.99))
							.quantity(15).build();

			Item item7 = Item.builder()
							.name("Dress")
							.brand(Brand.Aritzia)
							.establishmentYear(2022)
							.price(new BigDecimal(49.99))
							.quantity(15).build();

			Item item8 = Item.builder()
							.name("Wool Coat")
							.brand(Brand.Aritzia)
							.establishmentYear(2021)
							.price(new BigDecimal(110.99))
							.quantity(15).build();

			Item item9 = Item.builder()
							.name("Shorts")
							.brand(Brand.Nike)
							.establishmentYear(2022)
							.price(new BigDecimal(44.50))
							.quantity(15).build();

			DistributionCenter center1 = DistributionCenter.builder()
											.name("Toronto Distribution Center")
											.items(Arrays.asList(item1, item2, item4, item7, item8))
											.latitude(43.6532)
											.longitude(-79.2000).build();

			DistributionCenter center2 = DistributionCenter.builder()
											.name("Brampton Distribution Center")
											.latitude(43.7315)
											.longitude(-79.7624).build();

			DistributionCenter center3 = DistributionCenter.builder()
										.name("Mississauga Distribution Center")
										.latitude(43.5890)
										.longitude(-79.6441).build();

			DistributionCenter center4 = DistributionCenter.builder()
										.name("Hamilton Distribution Center")
										.latitude(43.2557)
										.longitude(-79.8711).build();

			distributionCenterRepository.save(DistributionCenter.builder()
					.name("Mississauga Distribution Center")
					.latitude(43.5890)
					.longitude(-79.6441).build());

			distributionCenterRepository.save(DistributionCenter.builder()
					.name("Hamilton Distribution Center")
					.latitude(43.2557)
					.longitude(-79.8711).build());

			distributionCenterRepository.save(center1);


		};
	}
}
