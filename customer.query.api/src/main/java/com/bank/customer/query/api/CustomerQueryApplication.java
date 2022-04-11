package com.bank.customer.query.api;

import com.bank.customer.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class CustomerQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerQueryApplication.class, args);
	}

}
