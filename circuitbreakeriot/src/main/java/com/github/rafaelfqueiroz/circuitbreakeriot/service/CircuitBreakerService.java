package com.github.rafaelfqueiroz.circuitbreakeriot.service;

public interface CircuitBreakerService {

	<T> T  executeGetRequest(String url, Class<T> responseType, String cacheKey);
	<R> R responseFallback(String url, Class<R> responseType, String cacheKey)  throws Exception;
	
}
