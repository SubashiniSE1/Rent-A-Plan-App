package com.hcl.capstoneproject.RentAPlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;

@EnableEurekaClient
@SpringBootApplication
public class RentAPlaceApplication {
	
     public static void main(String[] args) {
		SpringApplication.run(RentAPlaceApplication.class, args);
		System.out.println("Welcome Spring boot");
	}
	


}
