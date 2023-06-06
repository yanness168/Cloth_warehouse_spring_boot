package com.cloth_warehouse.assignment_1.models;
import com.cloth_warehouse.assignment_1.repository.ClothesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClothesInventory {
    private final ClothesRepository clothesRepository;

    public ClothesInventory(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    public Iterable<Clothe> getClothes() {
        return clothesRepository.findAll();
    }

    public Clothe getClothe(Integer id) {
        return clothesRepository.findById(id).orElse(null);
    }

    public void saveClothe(Clothe c) {
        clothesRepository.save(c);
    }
}