package com.github.rafaelfqueiroz.circuitbreakeriot.fallbackStrategy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("cacheStrategy")
public class FallbackCacheStrategy extends AbstractFallbackStrategy {
	

}
