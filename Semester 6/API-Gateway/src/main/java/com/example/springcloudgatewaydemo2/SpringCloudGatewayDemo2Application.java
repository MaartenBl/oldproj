package com.example.springcloudgatewaydemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudGatewayDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayDemo2Application.class, args);
	}

}
