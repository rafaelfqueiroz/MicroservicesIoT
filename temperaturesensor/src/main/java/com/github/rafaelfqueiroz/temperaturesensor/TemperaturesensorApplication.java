package com.github.rafaelfqueiroz.temperaturesensor;

import org.eclipse.californium.core.CoapServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.github.rafaelfqueiroz.temperaturesensor.data.Temperature;
import com.github.rafaelfqueiroz.temperaturesensor.data.TemperatureHolder;

@SpringBootApplication
public class TemperaturesensorApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TemperaturesensorApplication.class, args);
		
		new CoapServer().start();
	}
	
	@Value("${defaultTemperature}")
	private Double defaultTemperature;
	
	@Bean
	@Scope
	public TemperatureHolder temperatureHolder() {
		return new TemperatureHolder(new Temperature(defaultTemperature));
	}
}
