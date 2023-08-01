package com.distributioncenter.Services;

import com.distributioncenter.Models.DistributionCenter;
import com.distributioncenter.Models.Item;
import com.distributioncenter.Repository.DistributionCenterRepository;
import com.distributioncenter.Repository.ItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionCenterService {

    private final DistributionCenterRepository distributionCenterRepository;
    private final ItemRepository itemRepository;

    public DistributionCenterService(DistributionCenterRepository distributionCentreRepository, ItemRepository itemRepository) {
        this.distributionCenterRepository = distributionCentreRepository;
        this.itemRepository = itemRepository;
    }

    // List all distribution centres
    public Iterable<DistributionCenter> findAll() {
        return distributionCenterRepository.findAll();
    }

    // Add item to distribution centre
    public Item addItem(Long distributionCentreId, Item item) {
        DistributionCenter distributionCenter = distributionCenterRepository.findById(distributionCentreId)
                .orElseThrow(() -> new IllegalArgumentException("Distribution Center not found"));
        item.setDistributionCentre(distributionCenter);
        return itemRepository.save(item);
    }

    // Delete item
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    // Request item by brand and name
    public List<Item> findItemsByBrandAndName(Item.Brand brand, String name) {
        return itemRepository.findByBrandAndName(brand, name);
    }
}
