package com.distributioncenter.Repository;

import com.distributioncenter.Models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandAndName(Item.Brand brand, String name);
}
