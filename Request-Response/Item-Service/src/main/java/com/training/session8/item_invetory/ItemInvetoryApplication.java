package com.training.session8.item_invetory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemInvetoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(ItemInvetoryApplication.class, args);
	}

}
