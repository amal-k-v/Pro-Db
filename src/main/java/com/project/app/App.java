package com.project.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.project.service", "com.project.repository","com.project.controller"})
@EntityScan("com.project.model")
@EnableJpaRepositories("com.project.repository")

public class App {
	
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	
	
	
	

	 
	
}
