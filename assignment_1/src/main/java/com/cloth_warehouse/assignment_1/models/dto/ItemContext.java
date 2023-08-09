package com.cloth_warehouse.assignment_1.models.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cloth_warehouse.assignment_1.models.dto.DistributionCenterContext;
import com.cloth_warehouse.assignment_1.models.Clothe;
import com.cloth_warehouse.assignment_1.models.Clothe.Brand;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private Brand brand;

    @Min(value = 2021, message = "Establishment year must be more than 2021")
    private int establishmentYear;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1.0", message = "Price must be more than 1.0")
    private BigDecimal price;

    private int quantity;

    public Clothe toClothe() {
        return Clothe.builder()
                .name(this.getName())
                .brand(this.getBrand())
                .establishmentYear(this.getEstablishmentYear())
                .price(this.getPrice())
                .build();
    }
}
