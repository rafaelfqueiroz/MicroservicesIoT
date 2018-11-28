package com.github.rafaelfqueiroz.temperaturesensor.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("sensor1")
public class TemperatureSerie1 implements TemperatureSerie {
	
	private Temperature temperature;
	
	private List<Temperature> serie = new ArrayList<>();
	
	public TemperatureSerie1(@Value("${initialTemperature}") Double initialTemperature) {
		this.temperature = new Temperature(initialTemperature);
		serie.add(new Temperature(25.0));
		serie.add(new Temperature(30.0));
		serie.add(new Temperature(32.0));
		serie.add(new Temperature(34.0));
		serie.add(new Temperature(34.0));
		serie.add(new Temperature(36.0));
		serie.add(new Temperature(35.0));
		serie.add(new Temperature(34.0));
		serie.add(new Temperature(32.0));
		serie.add(new Temperature(30.0));
		serie.add(new Temperature(28.0));
		serie.add(new Temperature(26.0));
	}
	
	public Double getTemperatureInTime(int time) {
		return serie.get(time).getValue();
	}
	
	public Double getLastTemperature() {
		return temperature.getValue();
	}
	
	public void changeTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	
}
