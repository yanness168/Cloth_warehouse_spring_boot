package com.cloth_warehouse.assignment_1.controllers;
import java.util.EnumSet;
import com.cloth_warehouse.assignment_1.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cloth_warehouse.assignment_1.models.Clothe;
import com.cloth_warehouse.assignment_1.models.Clothe.Brand;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/clothesForm")
public class FormController {

    @Autowired
    private ClothesRepository clothesRepository;

    @GetMapping
    public String clothesForm(Clothe clothe) { return "clothesForm"; }

    @ModelAttribute
    public Clothe clothe() {
        return Clothe
                .builder()
                .build();
    }

    @ModelAttribute
    public void brands(Model model) {
        var brandTypes = EnumSet.allOf(Brand.class);
        model.addAttribute("brandTypes", brandTypes);
    }

    @PostMapping
    public String validateClotheFormSubmission(@Valid Clothe clothe,
                                               BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "clothesForm";
        }

        System.out.println("Processed clothes item: {}" + clothe);
        clothesRepository.save(clothe);
        return "redirect:/clothesList";
    }
}


