package com.training.session11.cartfullreqresp.adapter.repository.rds;

import com.training.session11.cartfullreqresp.adapter.repository.rds.entity.CartAggregateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartAggregateJpaRepository extends JpaRepository<CartAggregateEntity, String> {
}
