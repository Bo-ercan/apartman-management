package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@SpringBootApplication(exclude =  SecurityAutoConfiguration.class)
public class ApartmanProjectApplication {

	public static void main(String[] args) {
		String activeProfile = System.getProperty("spring.profiles.active");
		if (activeProfile == null) {
			System.setProperty("spring.profiles.active", "dev");
		}
		SpringApplication.run(ApartmanProjectApplication.class, args);
	}

}
