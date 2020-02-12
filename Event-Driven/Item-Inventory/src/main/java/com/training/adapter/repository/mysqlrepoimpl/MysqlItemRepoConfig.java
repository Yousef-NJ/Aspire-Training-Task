package com.training.adapter.repository.mysqlrepoimpl;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "com.trainin.demo.mysql",name = "enabled",matchIfMissing = false)
public class MysqlItemRepoConfig {
	
	@Bean
	public MysqlItemRepo mysqlRepo(MysqlRepoJPA repo,ModelMapper mappe) {
		return new MysqlItemRepo(repo, mappe);		
	}

}
