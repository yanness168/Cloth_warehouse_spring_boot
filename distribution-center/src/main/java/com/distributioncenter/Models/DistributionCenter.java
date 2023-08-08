package com.distributioncenter.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name="DISTRIBUTION_CENTER")
@AllArgsConstructor
@NoArgsConstructor
public class DistributionCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(
            mappedBy="distributionCenter",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<Item> items;

    @JsonManagedReference
    public List<Item> getItems() {
        return this.items;
    }

    private double latitude;

    private double longitude;

    public DistributionCenter(String name,
                              double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
