package com.github.rafaelfqueiroz.temperaturesensor.data;

import java.util.Date;

public class Temperature {

	private Double value;
	private Date readTime;

	public Temperature() {}
	
	public Temperature(Double value) {
		this.value = value;
		this.setReadTime(new Date());
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
}
