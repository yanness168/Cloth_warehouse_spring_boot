package com.cloth_warehouse.assignment_1.repository;

import com.cloth_warehouse.assignment_1.models.Clothe;
import org.springframework.data.repository.CrudRepository;

public interface ClothesRepository extends CrudRepository<Clothe, Integer> {

    List<Clothe> findByBrandAndEstablishmentYear(String brand, establishmentYear year)
}
