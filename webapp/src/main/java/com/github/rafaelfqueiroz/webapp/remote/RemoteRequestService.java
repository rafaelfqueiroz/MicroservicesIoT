package com.github.rafaelfqueiroz.webapp.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class RemoteRequestService {
	
	@Value("${curcuitBreaker.enabled}")
	private static final String ENABLE_CB = "false";
	@Value("${curcuitBreaker.requestGateway}")
	private Boolean requestGateway = false;
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="fallbackResponse", commandProperties= {
			@HystrixProperty(name="circuitBreaker.enabled", value=ENABLE_CB)
	})
	public Double getTemperatureFromSensor(String sensorPort) {
		Double temperature = null;
		if (requestGateway) {
			temperature = restTemplate.getForObject("http://localhost:8081/sensor/temperature/"+sensorPort+"/now", Double.class);
		} else {
			temperature = restTemplate.getForObject("http://localhost:"+sensorPort+"/temperature/now", Double.class);
		}
		
		return temperature;
	}
	
	@HystrixCommand(fallbackMethod="fallbackResponseWithTime", commandProperties= {
			@HystrixProperty(name="circuitBreaker.enabled", value=ENABLE_CB)
	})
	public Double getTemperatureFromSensor(String sensorPort, Integer time) {
		Double temperature = null;
		if (requestGateway) {
			temperature = restTemplate.getForObject("http://localhost:8081/sensor/temperature/"+sensorPort+"/"+time, Double.class);
		} else {
			temperature = restTemplate.getForObject("http://localhost:"+sensorPort+"/temperature/"+time, Double.class);
		}
		
		return temperature;
	}
	
	@HystrixCommand(fallbackMethod="fallbackResponse")
	public Double putSensorTemperature(String sensorPort, Double temperatureUpdate) {
		Double newTemperature = null;
		if (requestGateway) {
			newTemperature = restTemplate.postForObject("http://localhost:8081/temperature/"+sensorPort, temperatureUpdate, Double.class);
		} else {
			newTemperature = restTemplate.postForObject("http://localhost:"+sensorPort+"/temperature", temperatureUpdate, Double.class);
		}
		return newTemperature;
	}
	
	@SuppressWarnings("unused")
	private Double fallbackResponse(String sensorPort) {
		return 0.0;
	}
	
	@SuppressWarnings("unused")
	private Double fallbackResponseWithTime(String sensorPort, Integer time) {
		return 0.0;
	}

}
