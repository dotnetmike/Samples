package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example")
@SpringBootApplication
public class PocGroupingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocGroupingApplication.class, args);
	}

}
