package com.github.rafaelfqueiroz.webapp.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class RemoteRequestService {
	
	@Value("${curcuitBreaker.enabled}")
	private Boolean enableCB = false;
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="fallbackResponse")
	public Double getTemperatureFromSensor(String sensorPort) {
		Double temperature = restTemplate.getForObject("http://localhost:"+sensorPort+"/temperature/now", Double.class);
		
		return temperature;
	}
	
	@HystrixCommand(fallbackMethod="fallbackResponse")
	public Double putSensorTemperature(String sensorPort, Double temperatureUpdate) {
		Double newTemperature = restTemplate.postForObject("http://localhost:"+sensorPort+"/temperature", temperatureUpdate, Double.class);
		return newTemperature;
	}
	
	@SuppressWarnings("unused")
	private Double fallbackResponse() {
		return 0.0;
	}

}
