package com.github.rafaelfqueiroz.circuitbreakeriot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.circuitbreakeriot.delegate.CBSensorDelegate;


@RestController
@RequestMapping("/sensor/temperature")
public class CBTemperatureController {

	@Autowired
	private CBSensorDelegate delagete;
	
	@Caching(cacheable= {
			@Cacheable(value="normal", key="#sensorId", unless="#result == 0.0", cacheManager="normalCacheManager")
	})
	@GetMapping("/{sensorId}/now")
	public Double checkTemperatureOfSensor(@PathVariable("sensorId") int sensorId) {
		Double response = delagete.getTemperatureFromSensor(sensorId);
		return response;
	}
	
	@GetMapping("/{sensorId}/{time}")
	public Double checkTemperatureOfSensorInTime(@PathVariable("sensorId") int sensorId, @PathVariable("time") Integer time) {
		Double response = delagete.getTemperatureFromSensor(sensorId);
		return response;
	}
	
}
