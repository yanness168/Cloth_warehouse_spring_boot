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

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DistributionCenter getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }
}