package com.github.rafaelfqueiroz.circuitbreakeriot.fallbackStrategy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("exceptionStrategy")
public class FallbackExceptionStrategy  extends AbstractFallbackStrategy {

	@Override
	public Double getDefaultFallback(Integer sensorId) throws Exception {
		super.getDefaultFallback(sensorId);
		throw new Exception("Circuit Breaker has no valid value.");
	}
}
