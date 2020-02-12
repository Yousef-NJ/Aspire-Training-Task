package com.training.adapter.repo.rdsrepo.impl;

import com.training.adapter.repo.rdsrepo.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdsItemRepoJpa extends JpaRepository<ItemEntity, String>{
	

}
