package com.github.rafaelfqueiroz.temperaturesensor.data;

public class Temperature {

	private Double value;

	public Temperature() {}
	
	public Temperature(Double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
