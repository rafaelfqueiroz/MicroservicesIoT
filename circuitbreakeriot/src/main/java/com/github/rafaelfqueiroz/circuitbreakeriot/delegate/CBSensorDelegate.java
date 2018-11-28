package com.github.rafaelfqueiroz.circuitbreakeriot.delegate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class CBSensorDelegate {
	
	private Map<String, Double> cache = new HashMap<>();
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="responseFallback")
	public Double getTemperatureFromSensor(String sensorId) {
		Double response = restTemplate.getForObject("http://localhost:"+ sensorId +"/temperature/now", Double.class);
		cache.put(sensorId, response);
		return response;
	}
	
	@HystrixCommand(fallbackMethod="responseFallbackWithTime")
	public Double getTemperatureFromSensor(String sensorId, Integer time) {
		Double response = restTemplate.getForObject("http://localhost:"+ sensorId +"/temperature/"+time, Double.class);
		cache.put(sensorId, response);
		return response;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Double responseFallback(String sensorId) {
		if (cache.containsValue(sensorId)) {
			return cache.get(sensorId);
		}
		return 0.0;
	}
	
	public Double responseFallbackWithTime(String sensorId,  Integer time) {
		return responseFallback(sensorId);
	}
	
}
