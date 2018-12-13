package com.github.rafaelfqueiroz.circuitbreakeriot.service;

public interface CircuitBreakerService {

	Double getTemperatureFromSensor(Integer sensorId);
	Double getTemperatureFromSensor(String sensorId, Integer time);
	Double responseFallback(Integer sensorId)  throws Exception;
	Double responseFallbackWithTime(Integer sensorId,  Integer time) throws Exception;
	
}
