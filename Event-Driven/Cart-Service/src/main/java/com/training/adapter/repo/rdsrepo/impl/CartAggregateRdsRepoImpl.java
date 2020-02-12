package com.training.adapter.repo.rdsrepo.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.training.adapter.repo.rdsrepo.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import com.training.adapter.repo.CartAggregateRepo;
import com.training.adapter.repo.rdsrepo.entity.CartAggregateEntity;
import com.training.model.CartAggregate;
import com.training.model.CartItem;

@Component
public class CartAggregateRdsRepoImpl implements CartAggregateRepo{
	private final RdsCartAggregateRepoJpa repo;
	
	public CartAggregateRdsRepoImpl(RdsCartAggregateRepoJpa repo) {
		this.repo = repo;
	}

	@Override
	public void saveCartAggregate(CartAggregate cartAggregate) {
		repo.save(mapToEntity(cartAggregate));
	}

	@Override
	public Optional<CartAggregate> loadCartAggregate(String cartNo) {
		Optional<CartAggregateEntity> optionalCartEntity = repo.findById(cartNo);
		if(optionalCartEntity.isPresent()) {
			return optionalCartEntity.map(this::mapToCartAggregate);			
		}
		return Optional.empty();
	}

	private CartAggregateEntity mapToEntity(CartAggregate cartAggregate) {
		List<CartItemEntity> items=cartAggregate.getItems().values().stream().map(item->new CartItemEntity(item.getItemNo(), item.getPrice(), item.getQuantity())).collect(Collectors.toList());
		return new CartAggregateEntity(cartAggregate.getUserId(), cartAggregate.getCartNo(), cartAggregate.getState(),items);
	}
	
	private CartAggregate mapToCartAggregate(CartAggregateEntity entity) {
		CartAggregate cartAggregate = new CartAggregate(entity.getUserId(), entity.getCartNo());
		Map<String,CartItem> items=entity.getItems().stream().map(cartEntity->new CartItem(cartEntity.getItemNo(), cartEntity.getPrice(), cartEntity.getQuantity())).collect(Collectors.toMap(item->item.getItemNo(), item->item));
		cartAggregate.setState(entity.getState());
		cartAggregate.setItems(items);
		return cartAggregate;
	}

}
