package com.distributioncenter.Models.dto;

import java.math.BigDecimal;

import com.distributioncenter.Models.DistributionCenter;
import com.distributioncenter.Models.Item;
import com.distributioncenter.Models.Item.Brand;


public class CreateItem {
    private String name;

    private Brand brand;

    private int establishmentYear;

    private BigDecimal price;

    private int quantity;

    private DistributionCenter distributionCenter;

    public Item toItem() {
        return new Item(name, brand, establishmentYear, price, quantity, distributionCenter);
    }
}