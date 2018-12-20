package com.github.rafaelfqueiroz.circuitbreakeriot.fallbackStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public abstract class AbstractFallbackStrategy implements FallbackStrategy<String>{
	
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss");
	
	@Cacheable(value="fallback", key="#sensorId", condition = "#result != 0.0", cacheManager="failureCacheManager")
	public <T> T getDefaultFallback(String sensorId) throws Exception {
		System.out.println(String.format(" >>> [sensor %s - %s]: Non cache value.", sensorId, formatadorData.format(new Date())));
		return null;
	}
	
	@CachePut(value="fallback", key="#sensorId", condition = "#result != 0.0", cacheManager="failureCacheManager")
	public <T> T updateDefaultValue(String sensorId, T value) {
		return value;
	}

}
