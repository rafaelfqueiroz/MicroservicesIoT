package com.github.rafaelfqueiroz.circuitbreakeriot.fallbackStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public abstract class AbstractFallbackStrategy implements FallbackStrategy<Integer, Double>{
	
	private SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm:ss");
	
	@Cacheable(value="fallback", key="#sensorId", condition = "#result != 0.0", cacheManager="failureCacheManager")
	public Double getDefaultFallback(Integer sensorId) throws Exception {
		System.out.println(String.format(" >>> [sensor %d - %s]: Non cache value.", sensorId, formatadorData.format(new Date())));
		return 0.0;
	}
	
	@CachePut(value="fallback", key="#sensorId", condition = "#result != 0.0", cacheManager="failureCacheManager")
	public Double updateDefaultValue(Integer sensorId, Double value) {
		return value;
	}

}
