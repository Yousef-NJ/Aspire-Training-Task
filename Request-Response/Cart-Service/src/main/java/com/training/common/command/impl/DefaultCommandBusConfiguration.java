package com.training.common.command.impl;


import com.training.common.command.CommandBus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@ConditionalOnProperty(prefix = "com.aspire.training.default-command-bus"
    ,name="enabled",matchIfMissing = true
)

public class DefaultCommandBusConfiguration  {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CommandBus defaultCommandBus(){
        return new DefaultCommandBus();
    }
}
