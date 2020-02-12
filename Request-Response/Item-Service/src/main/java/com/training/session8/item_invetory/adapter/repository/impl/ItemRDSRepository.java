package com.training.session8.item_invetory.adapter.repository.impl;

import com.training.session8.item_invetory.adapter.repository.ItemRepository;
import com.training.session8.item_invetory.adapter.repository.entity.ItemEntity;
import com.training.session8.item_invetory.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

 class ItemRDSRepository implements ItemRepository {

    private final ItemJpaRepository itemJpaRepository;
    private final ModelMapper modelMapper;

    public ItemRDSRepository(ItemJpaRepository itemJpaRepository, ModelMapper modelMapper) {
        this.itemJpaRepository = itemJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Item addItem(Item item) {
        return mapToModel(itemJpaRepository.save(mapToEntity(item)));
    }

    @Override
    public Page<Item> loadItems(int pageIndex, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return itemJpaRepository
                .findAll(pageRequest)
                .map(this::mapToModel);
    }

    @Override
    public Optional<Item> loadItem(String itemNo) {
        return itemJpaRepository.findById(itemNo)
                .map(this::mapToModel);
    }

    private Item mapToModel(ItemEntity itemEntity){

        return modelMapper.map(itemEntity,Item.class);
    }

    private ItemEntity mapToEntity(Item item){

        return modelMapper.map(item,ItemEntity.class);
    }
}
