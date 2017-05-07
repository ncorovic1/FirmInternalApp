package com.vacations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class VacationsModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationsModuleApplication.class, args);
	}
}
