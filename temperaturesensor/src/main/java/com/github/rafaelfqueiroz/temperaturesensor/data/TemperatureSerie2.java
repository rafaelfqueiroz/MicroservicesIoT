package com.github.rafaelfqueiroz.temperaturesensor.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("sensor2")
public class TemperatureSerie2  implements TemperatureSerie {
	
	private List<Temperature> serie = new ArrayList<>();
	
	public TemperatureSerie2() {
		serie.add(new Temperature(24.0));
		serie.add(new Temperature(31.0));
		serie.add(new Temperature(33.0));
		serie.add(new Temperature(33.0));
		serie.add(new Temperature(35.0));
		serie.add(new Temperature(35.0));
		serie.add(new Temperature(37.0));
		serie.add(new Temperature(34.0));
		serie.add(new Temperature(32.0));
		serie.add(new Temperature(31.0));
		serie.add(new Temperature(27.0));
		serie.add(new Temperature(25.0));
	}

	public Double getTemperatureInTime(int time) {
		return serie.get(time).getValue();
	}
	
	public Double getLastTemperature() {
		return serie.get(serie.size()-1).getValue();
	}

}
