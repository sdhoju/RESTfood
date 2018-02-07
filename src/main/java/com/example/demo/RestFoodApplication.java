package com.example.demo;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJSONDoc
//
@ComponentScan({"controller","service","repository","client"})
@EntityScan
@EnableJpaAuditing
public class RestFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFoodApplication.class, args);
	}
}