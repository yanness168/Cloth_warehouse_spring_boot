package com.cloth_warehouse.assignment_1.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

import com.cloth_warehouse.assignment_1.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.apache.http.client.HttpClient;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

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
import com.cloth_warehouse.assignment_1.models.Coordinate;
import com.cloth_warehouse.assignment_1.repository.ClothesRepository;

@Controller
@RequestMapping("/manage")
@CrossOrigin(origins = "http://localhost:8082")
public class ManagementController {

    private final RestTemplate restTemplate;

    private List<DistributionCenterContext> distributionCenters;

    private List<ItemContext> filteredItems;

    private Location location;

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    public ManagementController(RestTemplate restTemplate,
                                List<DistributionCenterContext> distributionCenters,
                                List<ItemContext> filteredItems,
                                Location location) {
        this.distributionCenters = distributionCenters;
        this.filteredItems = filteredItems;
        this.location = location;
        this.restTemplate = restTemplate;
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
            location.distributionCenters = this.distributionCenters;
            location.filteredItems = this.filteredItems;
            Coordinate closestCenterCoordinates = location.findClosestCenterByCoordinate(
                    Location.WAREHOUSE_LATITUDE,
                    Location.WAREHOUSE_LONGITUDE);

            var closestDistributionCenter = restTemplate
                    .getForObject("http://localhost:8082/api/distribution-center/centers/" +
                            location.closestCenterId, DistributionCenterContext.class);

            String filteredItemName = this.filteredItems.get(0).getName();
            ItemContext clothesItem = closestDistributionCenter.getItems().stream()
                                        .filter(name -> filteredItemName.equals(name.getName()))
                                        .findAny()
                                        .orElse(null);

            updateInventoryAndWarehouse(clothesItem);

            return "redirect:/clothesList";
        } else {
            return "error";
        }

    }

    private void updateInventoryAndWarehouse(ItemContext item) {
        // Update warehouse with item
        var clothe = item.toClothe();
        clothesRepository.save(clothe);

        // Deduct quantity from inventory of distribution center
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", item.getId());
        restTemplate.put("http://localhost:8082/api/distribution-center/items/{id}", item, params);
    }
}
