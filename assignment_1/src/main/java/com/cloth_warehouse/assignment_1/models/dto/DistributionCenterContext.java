package com.cloth_warehouse.assignment_1.models.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.cloth_warehouse.assignment_1.models.dto.ItemContext;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistributionCenterContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distribution_center_id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private List<ItemContext> items;

    private double latitude;

    private double longitude;

    public DistributionCenterContext toDistributionCenterContext() {
        return DistributionCenterContext.builder()
                .name(this.getName())
                .items(this.getItems())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .build();
    }

}
