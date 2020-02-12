package com.training.adapter.repo.rdsrepo.impl;

import com.training.adapter.repo.rdsrepo.entity.CartAggregateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdsCartAggregateRepoJpa extends JpaRepository<CartAggregateEntity, String>{

}
