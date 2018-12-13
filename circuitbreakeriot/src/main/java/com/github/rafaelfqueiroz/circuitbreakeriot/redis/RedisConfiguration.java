
package com.github.rafaelfqueiroz.circuitbreakeriot.redis;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfiguration {
	
	@Autowired
	private Environment env;

	@Bean
	   public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
		redisConf.setHostName(env.getProperty("spring.redis.host"));
		redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
		redisConf.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
	        return new LettuceConnectionFactory(redisConf);
	   }
	
	@Primary
	@Bean("normalCacheManager")
	public CacheManager normalCacheManager() {
		RedisCacheManager failOverCacheManager = RedisCacheManager.builder(redisConnectionFactory())
											.cacheDefaults(
													RedisCacheConfiguration.defaultCacheConfig()
															.entryTtl(Duration.ofMillis(
																		env.getProperty("circuitBreaker.cache.expirationTime", Integer.class)))
															.disableCachingNullValues()
											)
											.transactionAware()
											.build(); 
		
		return failOverCacheManager;
	}
	
	@Bean("failureCacheManager")
	public CacheManager failureCacheManager() {
		
		Set<String> cacheNames = new HashSet<>();
		cacheNames.add("fallback");
		RedisCacheManager failOverCacheManager = RedisCacheManager.builder(redisConnectionFactory())
											.cacheDefaults(
													RedisCacheConfiguration.defaultCacheConfig()
															.entryTtl(Duration.ofMillis(
																		env.getProperty("circuitBreaker.cache.failureExpirationTime", Integer.class)))
															.disableCachingNullValues()
											)
											.initialCacheNames(cacheNames)
											.build();
		
		return failOverCacheManager;
	}
	
}
