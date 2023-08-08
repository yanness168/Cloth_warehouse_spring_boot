package com.distributioncenter.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DISTRIBUTION_CENTER")
public class DistributionCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distribution_center_id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;


    @JsonManagedReference
    @OneToMany(mappedBy="distributionCenter", cascade = CascadeType.ALL)
    private List<Item> items;

    private double latitude;

    private double longitude;
}
