package com.aiims.inspectionplan.restapiservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class RestApiServiceCorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiServiceCorsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://127.0.0.1:5173")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE")
	                    .allowCredentials(true)
	                    .maxAge(3600);
	        }
	    };
	}
}