package com.example.gymsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.gymsystem"})
public class GymSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymSystemApplication.class, args);
	}

}
