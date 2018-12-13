package com.github.rafaelfqueiroz.webapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.rafaelfqueiroz.webapp.app1.service.TemperatureSensorService;

@Profile("app1")
@Component
public class AppClient1 implements ApplicationRunner {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TemperatureSensorService service;
	
	@Value("${interval}")
	private Long interval;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		while (true) {
			Double averageTemperature = service.getAverageTemperature();
			System.out.println("Average temperature = " + new BigDecimal(averageTemperature).setScale(2, RoundingMode.UP).doubleValue() + ". [" + new Date() + "]");
			
			Thread.sleep(interval);
		}
	}

}
