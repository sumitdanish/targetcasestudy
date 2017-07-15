package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = "com.example")
@EnableCircuitBreaker
@SpringBootApplication
public class ElbAsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElbAsServiceApplication.class, args);
	}
}
