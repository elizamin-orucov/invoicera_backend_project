package com.business_data_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusinessDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessDataServiceApplication.class, args);
	}

}
