package com.training.service;

import org.springframework.stereotype.Service;

import com.training.adapter.repo.ItemRepo;
import com.training.model.Item;

import java.util.Optional;

@Service
public class ItemService {
	private final ItemRepo repo;

	public ItemService(ItemRepo repo) {
		this.repo = repo;
	}
    
    public Item addItem(Item item) {
    	return repo.addItem(item);
    }
	
	public boolean removeItem(String itemNo) {
		return repo.removeItem(itemNo);
	}

	public Optional<Item> getItem(String itemNo) { return repo.getItem(itemNo); }

}
