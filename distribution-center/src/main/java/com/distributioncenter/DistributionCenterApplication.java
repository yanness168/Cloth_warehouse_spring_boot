package com.distributioncenter;

import java.math.BigDecimal;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import org.modelmapper.ModelMapper;


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
			DistributionCenter distributionCenter1 = new DistributionCenter("Toronto Distribution Center", 43.6532, -79.2000);
			DistributionCenter distributionCenter2 = new DistributionCenter("Brampton Distribution Center", 43.7315, -79.7624);
			DistributionCenter distributionCenter3 = new DistributionCenter("Mississauga Distribution Center", 43.5890, -79.6441);
			DistributionCenter distributionCenter4 = new DistributionCenter("Hamilton Distribution Center", 43.2557, -79.8711);

			// Distribution Center 1 Items
			Item shirt1 = new Item("Shirt", Brand.Nike, 2023, new BigDecimal(39.99), 10, distributionCenter1);
			Item hat1 = new Item("Hat", Brand.Uniqlo, 2022, new BigDecimal(40.99), 20, distributionCenter1);
			Item sweater1 = new Item("Sweater", Brand.Zara, 2023, new BigDecimal(55.99), 5, distributionCenter1);

			List<Item> itemsList1 = Arrays.asList(shirt1, hat1, sweater1);
			distributionCenter1.setItems(itemsList1);
			distributionCenterRepository.save(distributionCenter1);

			// Distribution Center 2 Items
			Item sweater2 = new Item("Sweater", Brand.Zara, 2023, new BigDecimal(55.99), 5, distributionCenter2);
			Item shoes1 = new Item("Running Shoes", Brand.Adidas, 2021, new BigDecimal(89.99), 4, distributionCenter2);
			Item jeans1 = new Item("Jeans", Brand.Zara, 2022, new BigDecimal(68.99), 24, distributionCenter2);
			Item woolcoat1 = new Item("Wool Coat", Brand.Aritzia, 2021, new BigDecimal(99.99), 10, distributionCenter2);

			List<Item> itemsList2 = Arrays.asList(sweater2, shoes1, jeans1, woolcoat1);
			distributionCenter2.setItems(itemsList2);
			distributionCenterRepository.save(distributionCenter2);

			// Distribution Center 3 Items
			Item dress1 = new Item("Dress", Brand.Aritzia, 2022, new BigDecimal(65.99), 15, distributionCenter3);
			Item woolcoat2 = new Item("Wool Coat", Brand.Aritzia, 2021, new BigDecimal(99.99), 10, distributionCenter3);
			Item shorts1 = new Item("Shorts", Brand.Nike, 2022, new BigDecimal(49.99), 20, distributionCenter3);
			Item sweater3 = new Item("Sweater", Brand.Zara, 2023, new BigDecimal(55.99), 5, distributionCenter3);

			List<Item> itemsList3 = Arrays.asList(dress1, woolcoat2, sweater3);
			distributionCenter3.setItems(itemsList3);
			distributionCenterRepository.save(distributionCenter3);

			// Distribution Center 4 Items
			Item hat2 = new Item("Hat", Brand.Uniqlo, 2022, new BigDecimal(40.99), 20, distributionCenter4);
			Item longsleeve1 = new Item("Long Sleeve Shirt", Brand.Uniqlo, 2022, new BigDecimal(39.99), 30, distributionCenter4);
			Item shorts2 = new Item("Shorts", Brand.Nike, 2022, new BigDecimal(49.99), 20, distributionCenter4);
			Item dress2 = new Item("Dress", Brand.Aritzia, 2022, new BigDecimal(65.99), 15, distributionCenter4);

			List<Item> itemsList4 = Arrays.asList(hat2, longsleeve1, shorts2, dress2);
			distributionCenter4.setItems(itemsList4);
			distributionCenterRepository.save(distributionCenter4);

		};
	}
}
