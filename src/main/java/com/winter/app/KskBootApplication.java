package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KskBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KskBootApplication.class, args);
	}

}
