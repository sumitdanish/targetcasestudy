package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(basePackages = "com.example")
@SpringBootApplication
public class UserKartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserKartServiceApplication.class, args);
	}
}
