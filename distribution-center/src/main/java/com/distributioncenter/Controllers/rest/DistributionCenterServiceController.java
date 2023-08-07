package com.distributioncenter.Controllers.rest;

import java.util.Optional;

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

import jakarta.validation.Valid;

import com.distributioncenter.Models.Item;
import com.distributioncenter.Models.Item.Brand;
import com.distributioncenter.Models.DistributionCenter;
import com.distributioncenter.Models.dto.CreateItem;
import com.distributioncenter.Repository.DistributionCenterRepository;
import com.distributioncenter.Repository.ItemRepository;

@RestController
@RequestMapping(path="/api/distribution-center", produces="application/json")
public class DistributionCenterServiceController {

    private final DistributionCenterRepository distributionCenterRepository;

    private final ItemRepository itemRepository;

    public DistributionCenterServiceController(DistributionCenterRepository distributionCenterRepository,
                                               ItemRepository itemRepository) {
        this.distributionCenterRepository = distributionCenterRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/centers")
    public Iterable<DistributionCenter> allDistributionCenters() {
        return distributionCenterRepository.findAll();
    }

    @PostMapping
    public Item createItem(@Valid @RequestBody CreateItem item) {
        return itemRepository.save(item.toItem());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/items")
    public Iterable<Item> getItems(@RequestParam("brand") Optional<Brand> brand,
                                   @RequestParam("name") Optional<String> name) {
        if (!brand.isPresent() && !name.isPresent()) {
            return itemRepository.findAll();
        } else {
            return itemRepository.findByBrandAndName(brand.get(), name.get());
        }
    }

}