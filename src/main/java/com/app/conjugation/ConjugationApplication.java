package com.app.conjugation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories( basePackages = {"com.app.conjugation.repository"} )
//@EntityScan(basePackages = {"com.app.conjugation"})
@ComponentScan(basePackages = {"com.app.conjugation"})
public class ConjugationApplication {
	
	public static void main(String[] args) {
		/*
		 * SessionFactory sessionFactory; sessionFactory = new Configuration()
		 * .configure("hibernate.cfg.xml") .buildSessionFactory();
		 */
		SpringApplication.run(ConjugationApplication.class, args);
	}

}
