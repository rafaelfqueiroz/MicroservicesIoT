package com.github.rafaelfqueiroz.webapp.remote;

import java.util.ArrayList;
import java.util.List;

public class SensorRegistery {

	public static List<String> getPortOfSenros() {
		List<String> ports = new ArrayList<String>();
		ports.add("8091");
		ports.add("8092");
		ports.add("8093");
		return ports;
	}
	
}
