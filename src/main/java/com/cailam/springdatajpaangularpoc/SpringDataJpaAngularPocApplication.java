package com.cailam.springdatajpaangularpoc;

import com.cailam.springdatajpaangularpoc.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.cailam.springdatajpaangularpoc"})
public class SpringDataJpaAngularPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaAngularPocApplication.class, args);
	}
}
