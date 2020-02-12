package com.training.adapter.repository.mysqlrepoimpl;

import java.util.Optional;

import com.training.adapter.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.training.adapter.repository.mysqlrepoimpl.entity.ItemEntity;
import com.training.model.Item;

class MysqlItemRepo implements ItemRepository {
	private final MysqlRepoJPA repo;
	private final ModelMapper mapper;

	public MysqlItemRepo(MysqlRepoJPA repo,ModelMapper mappe) {
		this.repo = repo;
		this.mapper = mappe;
	}

	@Override
	public Item addItem(Item item) {
		repo.save(mapToItemEntity(item));
		return item;
	}

	@Override
	public Page<Item> loadItems(int pageIndex, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
		return repo.findAll(pageRequest).map(this::mapToItem);
	}

	@Override
	public Optional<Item> loadItem(String itemNo) {
		if(repo.existsById(itemNo)) {
			ItemEntity entity=repo.findById(itemNo).get();
			return Optional.of(mapToItem(entity));
		}
		return Optional.empty();
	}
	
	private Item mapToItem(ItemEntity entity) {
		return mapper.map(entity, Item.class);
	}
	
	private ItemEntity mapToItemEntity(Item item) {
		return mapper.map(item, ItemEntity.class);
	}

}
