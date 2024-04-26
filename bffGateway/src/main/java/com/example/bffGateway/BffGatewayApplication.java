package com.example.bffGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableConfigurationProperties
public class BffGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffGatewayApplication.class, args);
	}

}
