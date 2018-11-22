package com.github.rafaelfqueiroz.circuitbreakeriot.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class CBSensorDelegate {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(defaultFallback="responseFallback")
	public Double getTemperatureFromSensor(String sensorId) {
		Double response = restTemplate.getForObject("http://localhost:8081/temperature/now", Double.class);
		return response;
	}
	
	public Double responseFallback() {
		return 0.0;
	}
	
}
