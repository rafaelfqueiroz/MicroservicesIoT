package com.github.rafaelfqueiroz.circuitbreakeriot.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class TemperatureSensorDelegate {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand
	public Double getSensorById(String id) {
		Double response = restTemplate.getForObject("", Double.class, id);
		return response;
	}
	
}
