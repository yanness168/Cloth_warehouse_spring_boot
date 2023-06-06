package com.cloth_warehouse.assignment_1.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Clothe {
    private Long id;
    private String name;
    private Brand brand;
    private int establishmentYear;
    private BigDecimal price;

    public enum Brand {
        NIKE("Nike"),
        LEVI("Levi"),
        UNIQLO("Uniqlo"),
        ZARA("Zara"),
        ARITZIA("Aritzia"),
        ADIDAS("Adidas");

        private String name;

        private Brand(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
