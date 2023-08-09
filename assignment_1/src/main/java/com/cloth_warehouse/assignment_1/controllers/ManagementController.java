package com.cloth_warehouse.assignment_1.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.EnumSet;

import com.cloth_warehouse.assignment_1.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.cloth_warehouse.assignment_1.models.dto.DistributionCenterContext;
import com.cloth_warehouse.assignment_1.models.dto.ItemContext;
import com.cloth_warehouse.assignment_1.models.dto.ItemByBrandNameDto;
import com.cloth_warehouse.assignment_1.models.Clothe.Brand;
import com.cloth_warehouse.assignment_1.models.Location;


@Controller
@RequestMapping("/manage")
@CrossOrigin(origins = "http://localhost:8082")
public class ManagementController {

    private final RestTemplate restTemplate;

    private List<DistributionCenterContext> distributionCenters;

    private List<ItemContext> filteredItems;


    @Autowired
    public ManagementController(RestTemplate restTemplate,
                                List<DistributionCenterContext> distributionCenters,
                                List<ItemContext> filteredItems) {
        this.distributionCenters = distributionCenters;
        this.restTemplate = restTemplate;
        this.filteredItems = filteredItems;
    }

    @ModelAttribute
    public void clothesByBrandName(Model model) {
        model.addAttribute("itemByBrandNameDto", new ItemByBrandNameDto());
    }

    @ModelAttribute
    public void brands(Model model) {
        var brandTypes = EnumSet.allOf(Brand.class);
        model.addAttribute("brandTypes", brandTypes);
    }

    // Get all distribution centers
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String showManagementPage(@AuthenticationPrincipal User user) {
        if (!user.getRole().contains("ADMIN")) {
            return "access-denied";
        }

        var loggedInUser = user.getUsername();
        System.out.println("Logged in user: " + loggedInUser);

        var centers = restTemplate
                .getForObject("http://localhost:8082/api/distribution-center/centers",
                        DistributionCenterContext[].class);

        this.distributionCenters = Arrays.asList(centers);
        return "manage";
    }

    // Get items by name and brand
    @RequestMapping(value={"/inventoryForm"}, method=RequestMethod.POST)
    public String getItemsByBrandAndName(@ModelAttribute ItemByBrandNameDto itemByBrandNameDto,
                                         Model model) {

        var items = restTemplate
                            .getForObject("http://localhost:8082/api/distribution-center/items?brand=" +
                             itemByBrandNameDto.getBrand() + "&name=" +
                             itemByBrandNameDto.getName(), ItemContext[].class);

        this.filteredItems = Arrays.asList(items);

        if (!this.filteredItems.isEmpty()) {
            var centersWithFilteredItem = Location.findAllCentersWithFilteredItem(this.distributionCenters, this.filteredItems);
            var filteredCentersCoordinates = Location.mapCentersByCoordinatesAndID(centersWithFilteredItem);
            var closestCenterCoordinates = Location.findClosestCenterCoordinate(filteredCentersCoordinates,
                    Location.WAREHOUSE_LATITUDE,
                    Location.WAREHOUSE_LONGITUDE);

        } else {
            return "error";
        }
        // model.addAttribute("items", filteredItems);
        return "manage";

    }


}
