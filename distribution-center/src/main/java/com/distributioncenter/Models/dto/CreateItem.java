package com.distributioncenter.Models.dto;

import java.math.BigDecimal;

import com.distributioncenter.Models.DistributionCenter;
import com.distributioncenter.Models.Item;
import com.distributioncenter.Models.Item.Brand;

import lombok.Data;

@Data
public class CreateItem {
    private String name;

    private Brand brand;

    private int establishmentYear;

    private BigDecimal price;

    private int quantity;

    public Item toItem() {
        return Item.builder()
                .name(this.getName())
                .brand(this.getBrand())
                .establishmentYear(this.getEstablishmentYear())
                .price(this.getPrice())
                .quantity(this.getQuantity())
                .build();
    }
}