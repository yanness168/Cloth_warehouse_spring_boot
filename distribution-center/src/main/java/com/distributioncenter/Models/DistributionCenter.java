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

    private double latitude;

    private double longitude;

    public DistributionCenter(String name,
                              double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // GETTERS AND SETTERS
    @JsonManagedReference
    public List<Item> getItems() {
        return this.items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
