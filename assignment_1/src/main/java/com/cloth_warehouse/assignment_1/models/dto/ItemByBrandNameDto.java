package com.cloth_warehouse.assignment_1.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.cloth_warehouse.assignment_1.models.Clothe.Brand;


@Data
@NoArgsConstructor
public class ItemByBrandNameDto {
    private Brand brand;
    private String name;
}