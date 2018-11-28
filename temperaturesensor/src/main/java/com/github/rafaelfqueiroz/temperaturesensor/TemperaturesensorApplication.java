package com.github.rafaelfqueiroz.temperaturesensor;

import org.eclipse.californium.core.CoapServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemperaturesensorApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TemperaturesensorApplication.class, args);
		//new CoapServer().start();
		
	}
	
}
