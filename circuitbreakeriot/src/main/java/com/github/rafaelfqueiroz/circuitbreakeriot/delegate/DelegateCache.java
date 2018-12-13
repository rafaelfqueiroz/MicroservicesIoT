package com.github.rafaelfqueiroz.circuitbreakeriot.delegate;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class DelegateCache {
	
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss");

	@Cacheable(value="fallback", key="#sensorId", condition = "#result != 0.0", cacheManager="failureCacheManager")
	public Double cachedTemperatureSensor(int sensorId) {
		System.out.println(String.format(" >>> [sensor %d - %s]: Non cache value.", sensorId, formatadorData.format(new Date())));
		return 0.0;
	}
	
	@CachePut(value="fallback", key="#sensorId", condition = "#result != 0.0", cacheManager="failureCacheManager")
	public Double updateCache(int sensorId, Double value) {
		return value;
	}
}
