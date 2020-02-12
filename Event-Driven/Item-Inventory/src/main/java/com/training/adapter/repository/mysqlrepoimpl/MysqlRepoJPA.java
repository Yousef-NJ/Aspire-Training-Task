package com.training.adapter.repository.mysqlrepoimpl;

import com.training.adapter.repository.mysqlrepoimpl.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MysqlRepoJPA extends JpaRepository<ItemEntity, String>{

}
