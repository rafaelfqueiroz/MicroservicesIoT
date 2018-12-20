package com.github.rafaelfqueiroz.webapp.remote;

public interface RemoteRequestService {
	
	Double getTemperatureFromSensor(String sensorPort);
	Double getTemperatureFromSensor(String sensorPort, Integer time);
	Double putSensorTemperature(String sensorPort, Double temperatureUpdate);
	
}
