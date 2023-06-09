package com.cloth_warehouse.assignment_1.components;

import com.cloth_warehouse.assignment_1.models.Clothe;
import com.cloth_warehouse.assignment_1.models.ClothesInventory;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Builder
public class InitialClothesLoader implements CommandLineRunner {
    private final ClothesInventory ci;

    @Autowired
    public InitialClothesLoader(ClothesInventory ci) {
        this.ci = ci;
    }

    @Override
    public void run(String... args) {
        List<Clothe> initialClothes = Arrays.asList(
                Clothe.builder()
                        .name("T-shirt")
                        .brand(Clothe.Brand.Adidas)
                        .establishmentYear(2022)
                        .price(new BigDecimal("2211.21"))
                        .build(),
                Clothe.builder()
                        .name("Jacket")
                        .brand(Clothe.Brand.Levi)
                        .establishmentYear(2022)
                        .price(new BigDecimal("3123.26"))
                        .build(),
                Clothe.builder()
                        .name("T-shirt2")
                        .brand(Clothe.Brand.Uniqlo)
                        .establishmentYear(2021)
                        .price(new BigDecimal("2123.12"))
                        .build()
        );

        initialClothes.forEach(ci::saveClothe);
    }
}
