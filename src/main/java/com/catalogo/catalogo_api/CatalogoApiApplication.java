package com.catalogo.catalogo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.catalogo.catalogo_api")
public class CatalogoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApiApplication.class, args);
	}

}
