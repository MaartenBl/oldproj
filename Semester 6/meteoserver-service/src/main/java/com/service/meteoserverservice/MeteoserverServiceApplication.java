package com.service.meteoserverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MeteoserverServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteoserverServiceApplication.class, args);
	}

}
