package com.cloth_warehouse.assignment_1.controllers;

import com.cloth_warehouse.assignment_1.models.ClothesInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import com.cloth_warehouse.assignment_1.models.Clothe;
import com.cloth_warehouse.assignment_1.models.Clothe.Brand;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;

@Controller
@Slf4j
@RequestMapping("/clothesForm")
public class FormController {

    private final ClothesInventory cp;

    @Autowired
    private FormController(ClothesInventory cp) {
        this.cp = cp;
    }

    @GetMapping
    public String clothesForm() { return "clothesForm"; }

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
        cp.saveClothe(clothe);
        return "redirect:/clothesForm";
    }
}


