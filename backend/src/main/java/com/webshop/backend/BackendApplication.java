package com.webshop.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.out.println(org.hibernate.Version.getVersionString());
		SpringApplication.run(BackendApplication.class, args);
	}

}
