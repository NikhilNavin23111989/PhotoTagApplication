package com.services;

import java.util.Properties;



public class ServiceRequest {
	
	
	
	private EventCodes event;
	
	private Properties payload;
	
	
	public ServiceRequest(EventCodes code,Properties p) {
		
		
		this.event = code;
		this.payload = p;
	}


	public EventCodes getEvent() {
		return event;
	}


	public void setEvent(EventCodes event) {
		this.event = event;
	}


	public Properties getPayload() {
		return payload;
	}


	public void setPayload(Properties payload) {
		this.payload = payload;
	}

}
