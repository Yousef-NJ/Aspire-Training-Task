package com.training.adapter.repo;

import java.util.Optional;

import com.training.model.CartAggregate;

public interface CartAggregateRepo {
	
	public void saveCartAggregate(CartAggregate cartAggregate);
	
	public Optional<CartAggregate> loadCartAggregate(String cartNo);

}
