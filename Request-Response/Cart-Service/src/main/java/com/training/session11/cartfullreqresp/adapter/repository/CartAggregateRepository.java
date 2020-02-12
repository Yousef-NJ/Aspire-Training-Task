package com.training.session11.cartfullreqresp.adapter.repository;


import com.training.session11.cartfullreqresp.aggregate.CartAggregate;

import java.util.Optional;

public interface CartAggregateRepository {

    void insert(CartAggregate cartAggregate);

    Optional<CartAggregate> findById(String cartId);

}
