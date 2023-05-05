package com.service.nuclearservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NuclearServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NuclearServiceApplication.class, args);
	}

}
