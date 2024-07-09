package com.mangoket.camunda;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class CamundaApp {

	public static void main(String[] args) {
		SpringApplication.run(CamundaApp.class, args);
	}

}
