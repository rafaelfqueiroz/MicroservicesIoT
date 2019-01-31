package com.github.rafaelfqueiroz.temperaturesensor.data;

import org.springframework.stereotype.Component;

@Component
public class TimeoutStatus {

	private Boolean timeoutFlag = false;
	
	public Boolean updateTimeoutFlag() {
		timeoutFlag = !timeoutFlag;
		return isTimeoutEnabled();
	}
	
	public Boolean isTimeoutEnabled() {
		return timeoutFlag;
	}
	
}
