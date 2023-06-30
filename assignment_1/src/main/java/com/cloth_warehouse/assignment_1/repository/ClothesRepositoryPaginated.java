package com.cloth_warehouse.assignment_1.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloth_warehouse.assignment_1.models.Clothe;

public interface ClothesRepositoryPaginated extends PagingAndSortingRepository<Clothe, Long> {

}