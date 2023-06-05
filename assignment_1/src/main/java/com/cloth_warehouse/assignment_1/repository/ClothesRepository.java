package com.cloth_warehouse.assignment_1.repository;

import com.cloth_warehouse.assignment_1.models.Cloth;
import org.springframework.data.repository.CrudRepository;

public interface ClothesRepository extends CrudRepository<Cloth, Integer> {
}
