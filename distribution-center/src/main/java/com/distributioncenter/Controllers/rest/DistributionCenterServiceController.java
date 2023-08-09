package com.distributioncenter.Controllers.rest;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

import com.distributioncenter.Models.Item;
import com.distributioncenter.Models.Item.Brand;
import com.distributioncenter.Models.DistributionCenter;
import com.distributioncenter.Models.dto.CreateItem;
import com.distributioncenter.Repository.DistributionCenterRepository;
import com.distributioncenter.Repository.ItemRepository;
import com.distributioncenter.Models.dto.DistributionCenterDto;


@RestController
@RequestMapping(path="/api/distribution-center")
public class DistributionCenterServiceController {

    private final DistributionCenterRepository distributionCenterRepository;

    private final ItemRepository itemRepository;

    public DistributionCenterServiceController(DistributionCenterRepository distributionCenterRepository,
                                               ItemRepository itemRepository) {
        this.distributionCenterRepository = distributionCenterRepository;
        this.itemRepository = itemRepository;
    }

    // Get all distribution centers
    @GetMapping("/centers")
    public Iterable<DistributionCenter> allDistributionCenters() {
        return distributionCenterRepository.findAll();
    }

    // Get distribution center by id
    @GetMapping("/centers/{id}")
    public ResponseEntity<DistributionCenter> getSingleDistributionCenter(@PathVariable Long id) {
        Optional<DistributionCenter> center = distributionCenterRepository.findById(id);

        if (center.isPresent()) {
            return new ResponseEntity<DistributionCenter>(center.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all items, OR get items by brand and name
    @GetMapping("/items")
    public Iterable<Item> getItems(@RequestParam("brand") Optional<Brand> brand,
                                   @RequestParam("name") Optional<String> name) {
        if (!brand.isPresent() && !name.isPresent()) {
            return itemRepository.findAll();
        } else {
            return itemRepository.findByBrandAndName(brand.get(), name.get());
        }
    }

    // Add item to item repository
    @PostMapping
    public Item createItem(@Valid @RequestBody CreateItem item) {
        return itemRepository.save(item.toItem());
    }

    // Delete item from item repository by id
    @DeleteMapping("items/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemRepository.deleteById(id);
    }
}