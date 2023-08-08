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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Brand brand;

    private int establishmentYear;

    private BigDecimal price;

    private LocalDate createdAt = LocalDate.now();

    private int quantity;

    @ManyToOne
    @JoinColumn(name="distributioncenter_id")
    private DistributionCenter distributionCenter;

    @JsonBackReference
    public DistributionCenter getDistributionCenter() {
        return this.distributionCenter;
    }

    public Item(String name, Brand brand, int establishmentYear, BigDecimal price,
                int quantity, DistributionCenter distributionCenter) {
        this.name = name;
        this.brand = brand;
        this.establishmentYear = establishmentYear;
        this.quantity = quantity;
        this.distributionCenter = distributionCenter;
    }

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
