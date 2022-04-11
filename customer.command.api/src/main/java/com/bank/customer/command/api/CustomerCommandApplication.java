package com.bank.customer.command.api;

import com.bank.customer.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class CustomerCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCommandApplication.class, args);
	}

}
