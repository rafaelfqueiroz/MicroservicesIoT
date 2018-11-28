package com.github.rafaelfqueiroz.temperaturesensor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.temperaturesensor.data.Temperature;
import com.github.rafaelfqueiroz.temperaturesensor.data.TemperatureSerie1;

@RestController
@RequestMapping("/temperature")
public class SensorRestController {
	
	@Autowired
	private TemperatureSerie1 holder;

	@GetMapping("/now")
	public Double getCurrentTemperature() {
		return holder.getLastTemperature();
	}
	
	@GetMapping("/{time}")
	public Double getTemperature(@PathVariable("time") Integer time) {
		return holder.getTemperatureInTime(time);
	}
	
	@PostMapping
	public Double modifyTemperature(@RequestBody Double temperature) {
		holder.changeTemperature(new Temperature(temperature));
		return holder.getLastTemperature();
	}
	
}
