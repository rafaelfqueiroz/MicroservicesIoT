package com.github.rafaelfqueiroz.circuitbreakeriot.fallbackStrategy;

public interface FallbackStrategy<T> {

	<R> R getDefaultFallback(T parameter) throws Exception;
	<R> R updateDefaultValue(T parameter, R newValue);
	
}
