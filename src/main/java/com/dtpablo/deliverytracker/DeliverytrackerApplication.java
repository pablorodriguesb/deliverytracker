package com.dtpablo.deliverytracker;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class DeliverytrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverytrackerApplication.class, args);
	}

}
