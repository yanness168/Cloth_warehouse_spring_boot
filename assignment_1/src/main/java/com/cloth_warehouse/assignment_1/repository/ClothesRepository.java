package com.cloth_warehouse.assignment_1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cloth_warehouse.assignment_1.models.Clothe;
import com.cloth_warehouse.assignment_1.models.Clothe.Brand;


public interface ClothesRepository extends CrudRepository<Clothe, Long> {

    List<Clothe> findByBrandAndEstablishmentYear(Brand brand, int establishmentYear);

    List<Clothe> findByOrderByBrandDesc();
}
