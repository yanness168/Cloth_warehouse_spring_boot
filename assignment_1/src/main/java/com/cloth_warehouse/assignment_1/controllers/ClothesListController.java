package com.cloth_warehouse.assignment_1.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloth_warehouse.assignment_1.model.dto.ClothesBrandDateDto;
import com.cloth_warehouse.assignment_1.repository.ClothesRepository;
import com.cloth_warehouse.assignment_1.repository.ClothesRepositoryPaginated;

@Controller
@RequestMapping("/clothesList")
public class ClothesListController {
    private static final int PAGE_SIZE = 5;
    private ClothesRepository clothesRepository;

    private ClothesRepositoryPaginated clothesRepositoryPaginated;

    public ClothesListController(ClothesRepository clothesRepository,
                                 ClothesRepositoryPaginated clothesRepositoryPaginated) {
        this.clothesRepository = clothesRepository;
        this.clothesRepositoryPaginated = clothesRepositoryPaginated;
    }

    @GetMapping
    public String showClothes(Model model) { return "clothesList" }

    @ModelAttribute
    public void clothes(Model model) {
        var clothesPage = clothesRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("clothes", clothesPage);
        model.addAttribute("currentPage", clothesPage.getNumber());
        model.addAttribute("totalPages", clothesPage.getTotalPages());
    }

    @ModelAttribute
    public void clothesByBrandDate(Model model) {
        model.addAtribute("clothesByBrandDate", new ClothesBrandDateDto());
    }

    @PostMapping
    public String searchClothesByBrandDate(@ModelAttribute ClothesBrandDateDto clothesBrandDateDto,
                                           Model model) {
        model.addAttribute("clothes",
                clothesRepository.findByBrandAndEstablishmentYear(clothesBrandDateDto.getBrand(), 2022);

        return "clothesList";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
                             @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "clothesList";
        }

        var clothesPage = clothesRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("clothes", clothesPage.getContent());
        model.addAttribute("currentPage", clothesPage.getNumber());
        return "clothesList";
    }

}