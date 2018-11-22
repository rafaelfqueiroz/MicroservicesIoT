package com.github.rafaelfqueiroz.webapp.app1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.github.rafaelfqueiroz.webapp.remote.RemoteRequestService;
import com.github.rafaelfqueiroz.webapp.remote.SensorRegistery;

/**
 * Service responsible for Scenario 1. 
 * @author rafaelfdequeiroz
 */
@Profile("app1")
@Service
public class TemperatureSensorService {
	
	@Autowired
	private RemoteRequestService remoteService;

	/**
	 * Calculates de average of temperatures from the sensors. 
	 */
	public Double getAverageTemperature() {
		List<Double> temperatures = new ArrayList<>(); 
		for (String port : SensorRegistery.getPortOfSenros()) {
			temperatures.add(
						remoteService.getTemperatureFromSensor(port)
					);
		}
		return temperatures.stream().mapToDouble(a -> a).average().getAsDouble();
	}
	
}
