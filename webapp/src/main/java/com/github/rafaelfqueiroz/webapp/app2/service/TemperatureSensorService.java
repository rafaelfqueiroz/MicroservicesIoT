package com.github.rafaelfqueiroz.webapp.app2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.github.rafaelfqueiroz.webapp.remote.RemoteRequestService;
import com.github.rafaelfqueiroz.webapp.remote.SensorRegistery;

/**
 * Service responsible for Scenario 2. 
 * @author rafaelfdequeiroz
 */
@Profile("app2")
@Service
public class TemperatureSensorService {
	
	@Autowired
	private RemoteRequestService remoteService;

	public List<Double> getTemperatureFromSensors() {
		List<Double> temperatures = new ArrayList<>(); 
		for (String port : SensorRegistery.getPortOfSenros()) {
			temperatures.add(
						remoteService.getTemperatureFromSensor(port)
					);
		}
		return temperatures;
	}
	
	public void updateSensorTemperature(String sensorId, Double temperature) {
		remoteService.putSensorTemperature(sensorId, temperature);
	}

}
