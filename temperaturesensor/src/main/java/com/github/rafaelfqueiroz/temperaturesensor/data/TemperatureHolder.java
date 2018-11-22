package com.github.rafaelfqueiroz.temperaturesensor.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TemperatureHolder {
	
	@Value("${initialTemperature}")
	private Double initialTemperature;
	
	private Temperature temperature;
	
	public TemperatureHolder() {
		this.temperature = new Temperature(initialTemperature);
	}
	
	public Double getTemperature() {
		return temperature.getValue();
	}
	
	public void changeTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	
}
