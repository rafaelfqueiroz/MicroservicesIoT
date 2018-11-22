package com.github.rafaelfqueiroz.temperaturesensor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.temperaturesensor.data.Temperature;
import com.github.rafaelfqueiroz.temperaturesensor.data.TemperatureHolder;

@RestController
@RequestMapping("/temperature")
public class SensorRestController {
	
	@Autowired
	private TemperatureHolder holder;

	@GetMapping("/now")
	public Double getCurrentTemperature() {
		return holder.getTemperature();
	}
	
	@PostMapping
	public Double modifyTemperature(@RequestBody Double temperature) {
		holder.changeTemperature(new Temperature(temperature));
		return holder.getTemperature();
	}
	
}
