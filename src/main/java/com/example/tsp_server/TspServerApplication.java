package com.example.tsp_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.tsp_server.model"})
public class TspServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TspServerApplication.class, args);
	}

}
