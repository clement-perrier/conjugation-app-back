package com.app.conjugation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.conjugation.model.Tense;
import com.app.conjugation.repository.TenseRepository;

@SpringBootApplication(scanBasePackages = "com.app.conjugation")
@EnableJpaRepositories(
        basePackages = {"com.app.conjugation"}
)
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.app.conjugation"})
public class ConjugationApplication {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory;
        sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
		SpringApplication.run(ConjugationApplication.class, args);
	}

}
