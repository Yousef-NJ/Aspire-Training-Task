package com.training.session8.item_invetory.adapter.repository;

import com.training.session8.item_invetory.model.Item;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ItemRepository {

    public Item addItem(Item item);

    public Page<Item> loadItems(
            int pageIndex,
            int pageSize
    );

    public Optional<Item> loadItem(String itemNo);
}
