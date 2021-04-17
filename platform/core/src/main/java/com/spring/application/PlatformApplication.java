package com.spring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.backend.core", "com.backend.commons", "com.backend.api", "com.backend.service",
//		"com.backend.persistence" })
//@EnableConfigurationProperties
//@ConfigurationPropertiesScan( basePackages = {"com.backend.commons" ,"com.backend.core", "com.backend.api"  })
//@EntityScan(basePackages = { "com.backend.core", "com.backend.persistence" })
//@EnableJpaRepositories(basePackages = { "com.backend.core", "com.backend.persistence" })
//@EnableAsync
//@EnableScheduling
//@Configuration
public class PlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

}