package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class SpringEnotesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEnotesProjectApplication.class, args);
	}

}
