package com.pedro.depoimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class DepoimentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepoimentoApplication.class, args);
	}

}
