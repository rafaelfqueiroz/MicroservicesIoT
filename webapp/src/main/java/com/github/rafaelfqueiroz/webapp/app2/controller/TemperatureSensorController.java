package com.github.rafaelfqueiroz.webapp.app2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.rafaelfqueiroz.webapp.app2.service.TemperatureSensorService;

@Profile("app2")
@Controller
@RequestMapping("/sensor/temperature")
public class TemperatureSensorController {

	@Autowired
	private TemperatureSensorService service;
	
	@GetMapping("/temperatures")
	public String getAverageTemperature(Model model) {
		List<Double> temperatures = service.getTemperatureFromSensors();
		model.addAttribute("temperatures", temperatures);
		return "temperatures";
	}
}
