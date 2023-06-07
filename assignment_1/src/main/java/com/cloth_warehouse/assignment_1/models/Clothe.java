package com.cloth_warehouse.assignment_1.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("CLOTHES")
@Data
@Builder
public class Clothe {
    @Id
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    private Brand brand;

    @NotNull(message = "Establishment year is required")
    @Min(value = 2021, message = "Establishment year must be more than 2021")
    private int establishmentYear;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1000.0", message = "Price must be more than 1000")
    private BigDecimal price;

//    @CreatedDate
//    private Date createdAt = new Date();

    public enum Brand {
        Nike("Nike"),
        Levi("Levi"),
        Uniqlo("Uniqlo"),
        Zara("Zara"),
        Aritzia("Aritzia"),
        Adidas("Adidas");
        private final String title;

        private Brand(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
