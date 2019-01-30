package com.github.rafaelfqueiroz.webapp.app1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.rafaelfqueiroz.webapp.app1.service.TemperatureSensorService;

@Profile("app1")
@Controller
@RequestMapping("/sensor/temperature")
public class TemperatureSensorController {

	@Autowired
	private TemperatureSensorService service;
	
	@GetMapping("/average")
	public String getAverageTemperature(Model model) throws InterruptedException {
		Double average = service.getAverageTemperature();
		model.addAttribute("average", average);
		return "average";
	}
	
}
