package com.github.rafaelfqueiroz.webapp.remote;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("gatewayRequest")
public class GatewayRequestService  implements RemoteRequestService  {

	@Autowired
	private RestTemplate restTemplate;
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss.SSSSS");
	
	@Override
	public Double getTemperatureFromSensor(String sensorPort) {
		Double temperature = null;
		try {
			temperature = restTemplate.getForObject("http://localhost:8081/sensor/temperature/"+sensorPort+"/now", Double.class);
		} catch (Exception ex) {
			System.out.println("[" + formatadorData.format(new Date()) + "] " +  ex.getMessage());
			temperature = 0.0;
		}
		return temperature;
	}

	@Override
	public Double getTemperatureFromSensor(String sensorPort, Integer time) {
		Double temperature = null;
		try {
			temperature = restTemplate.getForObject("http://localhost:8081/sensor/temperature/"+sensorPort+"/"+time, Double.class);
		} catch (Exception ex) {
			System.out.println("[" + formatadorData.format(new Date()) + "] " +  ex.getMessage());
			temperature = 0.0;
		}
		return temperature;
	}

	@Override
	public Double putSensorTemperature(String sensorPort, Double temperatureUpdate) {
		// TODO implement
		return null;
	}

}
