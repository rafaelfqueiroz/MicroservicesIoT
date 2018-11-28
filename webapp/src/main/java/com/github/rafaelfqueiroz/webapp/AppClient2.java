package com.github.rafaelfqueiroz.webapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.rafaelfqueiroz.webapp.app2.service.TemperatureSensorService;

@Profile("app2")
@Component
public class AppClient2 implements ApplicationRunner {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TemperatureSensorService service;
	
	@Value("${interval}")
	private Long interval;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		while (true) {
			List<Double> temperatureFromSensors = service.getTemperatureFromSensors();
			int index = 1;
			for (Double temperature : temperatureFromSensors) {
				System.out.println("Temperatura Sensor [" + index +"] = "+ temperature);
				index++;
			}
			System.out.println();
			Thread.sleep(interval);
		}
	}

}
