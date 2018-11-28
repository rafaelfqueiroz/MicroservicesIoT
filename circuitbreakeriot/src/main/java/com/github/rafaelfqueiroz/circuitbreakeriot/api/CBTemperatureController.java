package com.github.rafaelfqueiroz.circuitbreakeriot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.circuitbreakeriot.delegate.CBSensorDelegate;


@RestController
@RequestMapping("/sensor/temperature")
public class CBTemperatureController {

	@Autowired
	private CBSensorDelegate delegate;
	
	@GetMapping("/{sensorId}/now")
	public Double checkTemperatureOfSensor(@PathVariable("sensorId") String sensorId) {
		return delegate.getTemperatureFromSensor(sensorId);
	}
	
	@GetMapping("/{sensorId}/{time}")
	public Double checkTemperatureOfSensorInTime(@PathVariable("sensorId") String sensorId, @PathVariable("time") Integer time) {
		return delegate.getTemperatureFromSensor(sensorId);
	}
	
}
