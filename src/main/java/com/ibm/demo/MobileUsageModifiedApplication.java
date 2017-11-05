package com.ibm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
// comment
@SpringBootApplication
@EnableMongoRepositories("com.ibm.demo.repository")
public class MobileUsageModifiedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileUsageModifiedApplication.class, args);
	}
	public void run() {
	}
	
}
