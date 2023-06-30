package com.cloth_warehouse.assignment_1.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.cloth_warehouse.assignment_1.models.Clothe.Brand;


@Data
@NoArgsConstructor
public class ClothesBrandDateDto {
    private Brand brand;
    private int establishmentYear;
}