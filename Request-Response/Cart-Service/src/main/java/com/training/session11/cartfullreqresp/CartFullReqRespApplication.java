package com.training.session11.cartfullreqresp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@ComponentScan("com.training")
@EnableCaching
public class CartFullReqRespApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartFullReqRespApplication.class, args);
	}

}
