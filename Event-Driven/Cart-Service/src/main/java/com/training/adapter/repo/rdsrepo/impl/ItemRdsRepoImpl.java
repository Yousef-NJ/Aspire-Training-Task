package com.training.adapter.repo.rdsrepo.impl;

import com.training.adapter.repo.rdsrepo.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.training.adapter.repo.ItemRepo;
import com.training.model.Item;

import java.util.Optional;

@Component
public class ItemRdsRepoImpl implements ItemRepo {
	private final RdsItemRepoJpa repo;
	private final ModelMapper mapper;

	public ItemRdsRepoImpl(RdsItemRepoJpa repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public Item addItem(Item item) {
		repo.save(mapToEntity(item));
		return item;
	}

	@Override
	public boolean removeItem(String itemNo) {
		if(repo.existsById(itemNo)) {
			repo.deleteById(itemNo);;
			return true;
		}
		return false;
	}

	@Override
	public Optional<Item> getItem(String itemNo) {
		Optional<ItemEntity> optionalEntity = repo.findById(itemNo);
		if(optionalEntity.isPresent()){
			return optionalEntity.map(this::mapToItem);
		}
		return Optional.empty();
	}

	private ItemEntity mapToEntity(Item item) {
		return mapper.map(item, ItemEntity.class);
	}

	private Item mapToItem(ItemEntity entity){
		return mapper.map(entity,Item.class);
	}

}
