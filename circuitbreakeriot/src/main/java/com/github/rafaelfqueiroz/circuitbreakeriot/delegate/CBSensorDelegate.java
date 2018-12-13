package com.github.rafaelfqueiroz.circuitbreakeriot.delegate;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class CBSensorDelegate {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DelegateCache cache;
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss");
	
	@HystrixCommand(fallbackMethod="responseFallback")
	public Double getTemperatureFromSensor(int sensorId) {
		System.out.println(String.format(" > [sensor %d - %s]: Starting remote call.", sensorId, formatadorData.format(new Date())));
		Double response = restTemplate.getForObject("http://localhost:"+ sensorId +"/temperature/now", Double.class);
		System.out.println(String.format(" > [sensor %d - %s]: Successful response (%1f).", sensorId, formatadorData.format(new Date()), response));
		cache.updateCache(sensorId, response);
		return response;
	}
	
	@Cacheable(value="fallback", key="#sensorId", cacheManager="normalCacheManager")
	@HystrixCommand(fallbackMethod="responseFallbackWithTime")
	public Double getTemperatureFromSensor(String sensorId, Integer time) {
		Double response = restTemplate.getForObject("http://localhost:"+ sensorId +"/temperature/"+time, Double.class);
		return response;
	}
	
	public Double responseFallback(int sensorId) {
		System.out.println(String.format(" > [sensor %d - %s]: Failure sensor.", sensorId, formatadorData.format(new Date())));
		Double cachedTemperature = cache.cachedTemperatureSensor(sensorId);
		
		System.out.println(String.format(" >>> [sensor %d - %s]: Cached temperature (%1f)", sensorId, formatadorData.format(new Date()), cachedTemperature));
		return cachedTemperature;
	}
	
	public Double responseFallbackWithTime(int sensorId,  Integer time) {
		return responseFallback(sensorId);
	}
	
}
