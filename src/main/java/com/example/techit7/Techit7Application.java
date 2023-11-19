package com.example.techit7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Techit7Application {

	public static void main(String[] args) {
		SpringApplication.run(Techit7Application.class, args);
	}

}
