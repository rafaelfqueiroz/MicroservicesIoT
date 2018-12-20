package com.github.rafaelfqueiroz.temperaturesensor.data.serie1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.rafaelfqueiroz.temperaturesensor.data.Temperature;
import com.github.rafaelfqueiroz.temperaturesensor.data.TemperatureSerie;

@Component
@Profile("sensor1")
public class TemperatureSerieImpl implements TemperatureSerie, ApplicationRunner {
	
	@Value("${serieFilePath}")
	private String serieFilePath;
	
	@Value("${lifeCycleTime}")
	private Integer lifeCycleMinutes = 0;
	private List<Temperature> serie = new ArrayList<>();
	private Integer time = 0;
	
	@PostConstruct
	private void loadTempeartureSerieFile() {
		try {
			List<String> lines = Files.readAllLines(Paths.get(serieFilePath));
			for (String line : lines) {
				String[] lineValues = line.split(";");
				for (String value : lineValues) {
					serie.add(new Temperature(Double.valueOf(value)));
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Double getTemperatureInTime(int time) {
		return serie.get(time).getValue();
	}
	
	public Double getLastTemperature() {
		return serie.get(time).getValue();
	}
	
	public void changeTemperature(Temperature temperature) {
	}
	
	private static final int MILLI = 1000;
	private static final int SEC = 60;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		int range = (lifeCycleMinutes * SEC) / serie.size();
		range = range == 0 ? 1 : range;
		int marker = 1;
		System.out.println("Time now [" + time + " from " + serie.size() + "]");
		while (time < serie.size()) {
			Thread.sleep(MILLI);
			System.out.println("SECONDs [" + marker + "/" + range + "]");
			if (marker == range) {
				marker = 1;
				time++;
				System.out.println("Time now [" + time + " from " + serie.size() + "]");
				if (time == serie.size()) {
					time = 0;
				}
				continue;
			}
			marker++;
		}
	}
	
}
