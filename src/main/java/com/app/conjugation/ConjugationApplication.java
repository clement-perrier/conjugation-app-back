package com.app.conjugation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@EnableJpaRepositories( basePackages = {"com.app.conjugation.repository"} )
//@EntityScan(basePackages = {"com.app.conjugation"})
@EnableScheduling
@ComponentScan(basePackages = { "com.app.conjugation" })
public class ConjugationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConjugationApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//					.allowedOrigins("*")
//	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//			}
//		};
//	}
}
