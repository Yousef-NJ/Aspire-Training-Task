package com.training.session8.item_invetory.adapter.repository.impl;

import com.training.session8.item_invetory.adapter.repository.entity.ItemEntity;
import com.training.session8.item_invetory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ItemJpaRepository extends JpaRepository<ItemEntity, String> {
}
