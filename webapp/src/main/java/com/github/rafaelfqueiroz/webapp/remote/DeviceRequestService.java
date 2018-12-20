package com.github.rafaelfqueiroz.webapp.remote;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.rafaelfqueiroz.circuitbreakeriot.service.CircuitBreakerService;

@Component
@Profile("deviceRequest")
public class DeviceRequestService implements RemoteRequestService  {
	
	@Value("${curcuitBreaker.enabled}")
	private static final String ENABLE_CB = "false";
	@Value("${curcuitBreaker.requestGateway}")
	private Boolean requestGateway = false;
	
	@Autowired
	private CircuitBreakerService cbService;
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss.SSSSS");
	
	public Double getTemperatureFromSensor(String sensorPort) {
		Double temperature = null;
		try {
			temperature = cbService.executeGetRequest("http://localhost:"+sensorPort+"/temperature/now", Double.class, sensorPort);
		} catch (Exception ex) {
			System.out.println("[" + formatadorData.format(new Date()) + "] " +  ex.getMessage());
			temperature = 0.0;
		}
		return temperature;
	}
	
	public Double getTemperatureFromSensor(String sensorPort, Integer time) {
		Double temperature = null;
		try {
			temperature = cbService.executeGetRequest("http://localhost:"+sensorPort+"/temperature/"+time, Double.class, sensorPort); 
		} catch (Exception ex) {
			System.out.println("[" + formatadorData.format(new Date()) + "] " +  ex.getMessage());
			temperature = 0.0;
		}
		
		return temperature;
	}
	
	public Double putSensorTemperature(String sensorPort, Double temperatureUpdate) {
		Double newTemperature = null;
		//	newTemperature = restTemplate.postForObject("http://localhost:8081/temperature/"+sensorPort, temperatureUpdate, Double.class);
		//	newTemperature = restTemplate.postForObject("http://localhost:"+sensorPort+"/temperature", temperatureUpdate, Double.class);
		return newTemperature;
	}
	
}
