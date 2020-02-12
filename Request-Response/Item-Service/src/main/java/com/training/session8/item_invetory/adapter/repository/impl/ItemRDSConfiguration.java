package com.training.session8.item_invetory.adapter.repository.impl;

import com.training.session8.item_invetory.adapter.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty
        (prefix = "com.training.item.rds",
        name = "enabled",
        matchIfMissing = true)

 class ItemRDSConfiguration {

    @Bean
    public ItemRepository rdsRepo(ItemJpaRepository itemJpaRepository,
                                  ModelMapper modelMapper){
        return new ItemRDSRepository(itemJpaRepository,modelMapper);
    }
}
