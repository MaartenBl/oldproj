package com.service.oilservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OilServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OilServiceApplication.class, args);
	}

}
