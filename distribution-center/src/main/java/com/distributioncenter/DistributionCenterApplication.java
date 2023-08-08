package com.distributioncenter;

import java.math.BigDecimal;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;


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
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository itemRepository,
										DistributionCenterRepository distributionCenterRepository) {
		return args -> {
			DistributionCenter distributionCenter = new DistributionCenter("Distribution Center 1", 40.44, 22.99);
			Item item1 = new Item("Shirt", Brand.Nike, 2023, new BigDecimal(39.99), 10, distributionCenter);
			Item item2 = new Item("Pants", Brand.Adidas, 2022, new BigDecimal(40.99), 10, distributionCenter);

			List<Item> items = Arrays.asList(item1, item2);
			distributionCenter.setItems(items);
			distributionCenterRepository.save(distributionCenter);

		};
	}
}
