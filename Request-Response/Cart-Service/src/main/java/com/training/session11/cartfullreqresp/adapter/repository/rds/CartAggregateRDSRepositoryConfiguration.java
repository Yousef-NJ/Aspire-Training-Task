package com.training.session11.cartfullreqresp.adapter.repository.rds;

import com.training.session11.cartfullreqresp.adapter.repository.CartAggregateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartAggregateRDSRepositoryConfiguration {

    @Bean
    public CartAggregateRepository createCartAggregateRepository(CartAggregateJpaRepository jpaRepository, ModelMapper modelMapper){
        return new CartAggregateRDSRepository(jpaRepository, modelMapper);
    }

}
