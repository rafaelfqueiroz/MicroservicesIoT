package com.github.rafaelfqueiroz.temperaturesensor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.temperaturesensor.data.Temperature;
import com.github.rafaelfqueiroz.temperaturesensor.data.TemperatureSerie;
import com.github.rafaelfqueiroz.temperaturesensor.data.TimeoutStatus;

@RestController
@RequestMapping("/temperature")
public class SensorRestController {
	
	@Autowired
	private TemperatureSerie holder;
	@Autowired
	private TimeoutStatus timeoutStatus;
	@Value("${timeout.sleepTime}")
	private Long sleepTime;

	@GetMapping("/now")
	public Double getCurrentTemperature() throws InterruptedException {
		if (timeoutStatus.isTimeoutEnabled()) {
			Thread.sleep(sleepTime);
		}
		return holder.getLastTemperature();
	}
	
	@GetMapping("/{time}")
	public Double getTemperature(@PathVariable("time") Integer time) throws InterruptedException {
		if (timeoutStatus.isTimeoutEnabled()) {
			Thread.sleep(sleepTime);
		}
		return holder.getTemperatureInTime(time);
	}
	
	@PostMapping
	public Double modifyTemperature(@RequestBody Double temperature) {
		holder.changeTemperature(new Temperature(temperature));
		return holder.getLastTemperature();
	}
	
	@GetMapping("/changeTimeoutFlag")
	public ResponseEntity<Boolean> changeTimeoutFlag() {
		return new ResponseEntity<Boolean>(timeoutStatus.updateTimeoutFlag(), HttpStatus.OK);
	}
	
}
