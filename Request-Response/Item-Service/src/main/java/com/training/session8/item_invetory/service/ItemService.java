package com.training.session8.item_invetory.service;

import com.training.session8.item_invetory.adapter.repository.ItemRepository;
import com.training.session8.item_invetory.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item newItem(Item item){
        Item addedItem = itemRepository.addItem(item);
        return addedItem;
    }

    public Page<Item> loadItems(int pageIndex, int pageSize){
        return itemRepository.loadItems(pageIndex, pageSize);
    }

    public Optional<Item> getItem(String itemNo){
        return itemRepository.loadItem(itemNo);
    }
}
