package com.example.demo;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableJSONDoc
@ComponentScan({"controller","service"})
@ComponentScan({"client"})
public class RestFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFoodApplication.class, args);
	}
}
