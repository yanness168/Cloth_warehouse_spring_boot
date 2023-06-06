package.com.cloth_warehouse.assignment1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloth_warehouse.assignment1.models.Clothe;
import com.cloth_warehouse.assignment1.models.Clothe.Brand;
import com.cloth_warehouse.assignment1.repository.impl.JdbcClotheRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/clothesForm")
public class FormController {

    @Autowired
    private JdbcClotheRepository clothesRepository;

    @GetMapping
    public String clothesForm() { return "clothesForm"; }

    @ModelAttribute
    public Clothe clothe() {
        return Clothe
                .builder()
                .build()
    }

    @PostMapping
    public String validateClotheFormSubmission(@Valid Clothe clothe,
                                               BindingResult result) {
        if (result.hasErrors()) {
            return "clothesForm"
        }
        System.out.println("Processed clothes item: {}", clothe);
        clothesRepository.save(clothe);
        return "redirect:/clothesForm"
    }
}


