package com.github.rafaelfqueiroz.temperaturesensor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfqueiroz.temperaturesensor.data.Temperature;
import com.github.rafaelfqueiroz.temperaturesensor.data.TemperatureHolder;

@RestController
@RequestMapping("/temperature")
public class TemperatureRestController {
	
	@Autowired
	private TemperatureHolder holder;

	@GetMapping("/now")
	public Temperature getCurrentTemperature() {
		return holder.getTemperature();
	}
	
}
