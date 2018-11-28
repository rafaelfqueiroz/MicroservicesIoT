package com.github.rafaelfqueiroz.temperaturesensor.data;

public interface TemperatureSerie {

	Double getLastTemperature();
	Double getTemperatureInTime(int time);
	
}
