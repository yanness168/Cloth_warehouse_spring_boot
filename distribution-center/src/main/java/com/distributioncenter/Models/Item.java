package com.distributioncenter.Models;

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

@Data
@Entity(name = "item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private Brand brand;

    @NotNull(message = "Establishment year is required")
    @Min(value = 2021, message = "Establishment year must be more than 2021")
    private int establishmentYear;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1000.0", message = "Price must be more than 1000")
    private BigDecimal price;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    private DistributionCenter distributionCenter;

    public void setDistributionCentre(DistributionCenter distributionCentre) {
        this.distributionCenter = distributionCentre;
    }

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
