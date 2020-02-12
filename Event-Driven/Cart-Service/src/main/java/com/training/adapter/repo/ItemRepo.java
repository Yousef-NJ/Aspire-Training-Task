package com.training.adapter.repo;

import com.training.model.Item;

import java.util.Optional;

public interface ItemRepo {
	
    public Item addItem(Item item);
	
	public boolean removeItem(String itemNo);

	public Optional<Item> getItem(String itemNo);

}
