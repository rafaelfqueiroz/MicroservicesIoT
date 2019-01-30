package com.github.rafaelfqueiroz.webapp.remote;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("noCBRequest")
public class NoCBRequestService implements RemoteRequestService  {
	
	@Autowired
	private RestTemplate restTemplate;
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss.SSSSS");
	
	@Value("${host}")
	private String host;
	
	@Override
	public Double getTemperatureFromSensor(String sensorPort) {
		Double temperature = null;
		try {
			temperature = restTemplate.getForObject("http://"+ host + ":"+sensorPort+"/temperature/now", Double.class, sensorPort);
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
			temperature = restTemplate.getForObject("http://"+ host + ":"+sensorPort+"/temperature/"+time, Double.class, sensorPort);
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
