package com.github.rafaelfqueiroz.circuitbreakeriot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.circuitbreakeriot.service.CircuitBreakerService;


@RestController
@RequestMapping("/sensor/temperature")
public class CBTemperatureController {

	@Autowired
	private CircuitBreakerService cbService;
	
	@Value("${host}")
	private String host;
	
	@Caching(cacheable= {
			@Cacheable(value="normal", key="#sensorId", unless="#result == 0.0", cacheManager="normalCacheManager")
	})
	@GetMapping("/{sensorId}/now")
	public Double checkTemperatureOfSensor(@PathVariable("sensorId") String sensorId) {
		Double response = cbService.executeGetRequest("http://"+ host + ":"+ sensorId +"/temperature/now", Double.class, sensorId);
		return response;
	}
	
	@GetMapping("/{sensorId}/{time}")
	public Double checkTemperatureOfSensorInTime(@PathVariable("sensorId") String sensorId, @PathVariable("time") Integer time) {
		Double response = cbService.executeGetRequest("http://"+ host + ":"+ sensorId +"/temperature/"+time, Double.class, sensorId);
		return response;
	}
	
}
