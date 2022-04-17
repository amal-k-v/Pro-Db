package com.project.configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class WebMvcConfig implements WebMvcConfigurer {
	
	
	 private final long MAX_AGE_SECS = 3600;

	    @Value("${app.cors.allowedOrigins}")
	    private String[] allowedOrigins;

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	    	System.out.println("Inside addCorsMappings");
	        registry.addMapping("/**")
	                .allowedOrigins(allowedOrigins)
	                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
	                .maxAge(MAX_AGE_SECS);
	    }

}
