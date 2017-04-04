package com.FirmInternalApp.TeamsModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TeamsModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsModuleApplication.class, args);
		System.out.println("Hello World!");
	}
}
