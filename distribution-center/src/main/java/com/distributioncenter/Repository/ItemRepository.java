package com.distributioncenter.Repository;

import com.distributioncenter.Models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBrandAndName(Item.Brand brand, String name);
}
